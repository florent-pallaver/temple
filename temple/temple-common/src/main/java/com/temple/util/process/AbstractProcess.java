package com.temple.util.process;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.temple.util.AbstractLogger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractProcess<P extends Progress, RESULT> extends AbstractLogger implements DataProcessor<RESULT> {

	public static final Redirect DEV_NULL_REDIRECT = Redirect.to(new File("/dev/null"));

	public static final int DEFAULT_TIMEOUT_IN_SECONDS = 20;

	@XmlTransient
	protected final P progress;

	@XmlTransient
	protected final Redirect output;

	@XmlTransient
	protected final Redirect error;

	@XmlTransient
	protected Process process;

	@XmlTransient
	protected int startTimeout;

	@XmlTransient
	protected int timeout;

	/** For JAX-B */
	protected AbstractProcess() {
		this(null);
	}

	protected AbstractProcess(P progress) {
		this(progress, DEV_NULL_REDIRECT, DEV_NULL_REDIRECT);
	}

	protected AbstractProcess(P progress, String output, String error) {
		this(progress, output == null ? DEV_NULL_REDIRECT : Redirect.to(new File(output)),
				error == null ? DEV_NULL_REDIRECT : Redirect.to(new File(error)));
	}

	protected AbstractProcess(P progress, Redirect output, Redirect error) {
		this.progress = progress;
		this.output = output == null ? DEV_NULL_REDIRECT : output;
		this.error = error == null ? DEV_NULL_REDIRECT : error;
		this.process = null;
		this.startTimeout = AbstractProcess.DEFAULT_TIMEOUT_IN_SECONDS;
	}

	@Override
	public P getProgress() {
		return this.progress;
	}

	protected abstract String[] createCommand() throws Exception;

	@Override
	public RESULT call() throws Exception {
		if (this.startProcess()) {
			try {
				// wait for process to start
				this.process.waitFor(this.startTimeout, TimeUnit.SECONDS);
				if (this.progress.hasStartedProcessing()) {
					if(this.timeout <= 0) {
						this.process.waitFor();
					} else {
						this.process.waitFor(this.timeout, TimeUnit.SECONDS);
					}
				}
			} catch (final InterruptedException e) {
				this.warning("%s interrupted", this);
			} finally {
				this.endProcess();
			}
		}
		return null;
	}

	protected boolean startProcess() throws Exception {
		final String[] command = this.createCommand();
		if (command != null && command.length > 0) {
			final ProcessBuilder pb = new ProcessBuilder(command);
			pb.redirectError(this.error);
			pb.redirectOutput(this.output);
			try {
				synchronized (this) {
					if (!this.progress.isDone()) {
						this.process = pb.start();
					}
				}
			} catch (final IOException e) {
				this.thrown("Failed to start process ...", e);
			}
		} else {
			this.warning("No command given...");
		}
		return this.process != null;
	}

 	protected void endProcess() {
		synchronized (this) {
			if (this.process != null
					// && this.process.isAlive() && this.progress.hasStartedProcessing()
					) {
				this.process.destroyForcibly();
				try {
					this.process.waitFor(AbstractProcess.DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
				} catch (final InterruptedException e) {
					this.ignored(e);
				}
			}
			this.progress.done();
		}
		if (this.process != null) {
			if (this.process.isAlive()) {
				this.warning("%s process could not be ended", this);
			} else {
				final int termination = this.process.exitValue();
				if (termination != 0) {
					this.error("%s failed with exit code %d", this, termination);
				}
			}
		}
	}

	@Override
	public void cancel() {
		this.endProcess();
	}

}
