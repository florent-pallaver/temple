package diet.service;

import java.util.stream.Stream;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

@AutoSignedIn
@Interceptor
@Dependent
public class AutoSignedInInterceptor {

	@Inject
	private HttpServletRequest request;

	@Inject
	private SessionBean session;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception { 
		if(!this.session.isSignedIn()) {
			final String subjectDN = request.getHeader("SSL_CLIENT_S_DN");
			final String subjectCN = Stream.of(subjectDN.split(","))
					.map(dnPart -> dnPart.split("="))
					.filter(dnParts -> "CN".equals(dnParts[0]))
					.map(dnParts -> dnParts[1])
					.findFirst()
					.orElse(null);
			if(subjectCN != null) {
				try {
					this.session.signIn(subjectCN, subjectCN);
				} catch (ServiceException e) {
				}
			}
		}
		return ctx.proceed();
	}

}
