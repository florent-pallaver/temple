package com.temple.util.geo;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.temple.util.json.DoubleHandler;
import com.temple.util.json.IntegerHandler;
import com.temple.util.json.JsonField;
import com.temple.util.json.JsonUtil;
import com.temple.util.json.Jsonable;

/**
 * Enumeration of all countries in the world!
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@XmlJavaTypeAdapter(CountryAdapter.class)
public enum Country implements GeoArea, Jsonable {

	// Source /resources/geonames/countryInfo_2015-03-25.txt
	// ordered by their ISO code

	Andorra("Andorra", "AD"),
	United_Arab_Emirates("United Arab Emirates", "AE"),
	Afghanistan("Afghanistan", "AF"),
	Antigua_Barbuda("Antigua and Barbuda", "AG"),
	Anguilla("Anguilla", "AI"),
	Albania("Albania", "AL"),
	Armenia("Armenia", "AM"),
	Netherlands_Antilles("Netherlands Antilles", "AN"),
	Angola("Angola", "AO"),
	Antarctica("Antarctica", "AQ"),
	Argentina("Argentina", "AR"),
	American_Samoa("American Samoa", "AS"),
	Austria("Austria", "AT"),
	Australia("Australia", "AU"),
	Aruba("Aruba", "AW"),
	Aland_Islands("Aland Islands", "AX"),
	Azerbaijan("Azerbaijan", "AZ"),
	Bosnia_Herzegovina("Bosnia and Herzegovina", "BA"),
	Barbados("Barbados", "BB"),
	Bangladesh("Bangladesh", "BD"),
	Belgium("Belgium", "BE"),
	Burkina_Faso("Burkina Faso", "BF"),
	Bulgaria("Bulgaria", "BG"),
	Bahrain("Bahrain", "BH"),
	Burundi("Burundi", "BI"),
	Benin("Benin", "BJ"),
	Barthelemy("Saint Barthelemy", "BL"),
	Bermuda("Bermuda", "BM"),
	Brunei("Brunei", "BN"),
	Bolivia("Bolivia", "BO"),
	Bonaire("Bonaire, Saint Eustatius and Saba ", "BQ"),
	Brazil("Brazil", "BR"),
	Bahamas("Bahamas", "BS"),
	Bhutan("Bhutan", "BT"),
	Bouvet_Island("Bouvet Island", "BV"),
	Botswana("Botswana", "BW"),
	Belarus("Belarus", "BY"),
	Belize("Belize", "BZ"),
	Canada("Canada", "CA"),
	Cocos_Islands("Cocos Islands", "CC"),
	Democratic_Republic_Congo("Democratic Republic of the Congo", "CD"),
	Central_African_Republic("Central African Republic", "CF"),
	Republic_Congo("Republic of the Congo", "CG"),
	Switzerland("Switzerland", "CH"),
	Ivory_Coast("Ivory Coast", "CI"),
	Cook_Islands("Cook Islands", "CK"),
	Chile("Chile", "CL"),
	Cameroon("Cameroon", "CM"),
	China("China", "CN"),
	Colombia("Colombia", "CO"),
	Costa_Rica("Costa Rica", "CR"),
	Serbia_Montenegro("Serbia and Montenegro", "CS"),
	Cuba("Cuba", "CU"),
	Cape_Verde("Cape Verde", "CV"),
	Curacao("Curacao", "CW"),
	Christmas_Island("Christmas Island", "CX"),
	Cyprus("Cyprus", "CY"),
	Czech_Republic("Czech Republic", "CZ"),
	Germany("Germany", "DE"),
	Djibouti("Djibouti", "DJ"),
	Denmark("Denmark", "DK"),
	Dominica("Dominica", "DM"),
	Dominican_Republic("Dominican Republic", "DO"),
	Algeria("Algeria", "DZ"),
	Ecuador("Ecuador", "EC"),
	Estonia("Estonia", "EE"),
	Egypt("Egypt", "EG"),
	Western_Sahara("Western Sahara", "EH"),
	Eritrea("Eritrea", "ER"),
	Spain("Spain", "ES"),
	Ethiopia("Ethiopia", "ET"),
	Finland("Finland", "FI"),
	Fiji("Fiji", "FJ"),
	Falkland_Islands("Falkland Islands", "FK"),
	Micronesia("Micronesia", "FM"),
	Faroe_Islands("Faroe Islands", "FO"),
	France("France", "FR"),
	Gabon("Gabon", "GA"),
	United_Kingdom("United Kingdom", "GB"),
	Grenada("Grenada", "GD"),
	Georgia("Georgia", "GE"),
	French_Guiana("French Guiana", "GF"),
	Guernsey("Guernsey", "GG"),
	Ghana("Ghana", "GH"),
	Gibraltar("Gibraltar", "GI"),
	Greenland("Greenland", "GL"),
	Gambia("Gambia", "GM"),
	Guinea("Guinea", "GN"),
	Guadeloupe("Guadeloupe", "GP"),
	Equatorial_Guinea("Equatorial Guinea", "GQ"),
	Greece("Greece", "GR"),
	South_Georgia_South_Sandwich_Islands("South Georgia and the South Sandwich Islands", "GS"),
	Guatemala("Guatemala", "GT"),
	Guam("Guam", "GU"),
	Guinea_Bissau("Guinea-Bissau", "GW"),
	Guyana("Guyana", "GY"),
	Hong_Kong("Hong Kong", "HK"),
	Heard_Island_McDonald_Islands("Heard Island and McDonald Islands", "HM"),
	Honduras("Honduras", "HN"),
	Croatia("Croatia", "HR"),
	Haiti("Haiti", "HT"),
	Hungary("Hungary", "HU"),
	Indonesia("Indonesia", "ID"),
	Ireland("Ireland", "IE"),
	Israel("Israel", "IL"),
	Isle_Man("Isle of Man", "IM"),
	India("India", "IN"),
	British_Indian_Ocean_Territory("British Indian Ocean Territory", "IO"),
	Iraq("Iraq", "IQ"),
	Iran("Iran", "IR"),
	Iceland("Iceland", "IS"),
	Italy("Italy", "IT"),
	Jersey("Jersey", "JE"),
	Jamaica("Jamaica", "JM"),
	Jordan("Jordan", "JO"),
	Japan("Japan", "JP"),
	Kenya("Kenya", "KE"),
	Kyrgyzstan("Kyrgyzstan", "KG"),
	Cambodia("Cambodia", "KH"),
	Kiribati("Kiribati", "KI"),
	Comoros("Comoros", "KM"),
	Saint_Kitts_Nevis("Saint Kitts and Nevis", "KN"),
	North_Korea("North Korea", "KP"),
	South_Korea("South Korea", "KR"),
	Kosovo("Kosovo", "XK"),
	Kuwait("Kuwait", "KW"),
	Cayman_Islands("Cayman Islands", "KY"),
	Kazakhstan("Kazakhstan", "KZ"),
	Laos("Laos", "LA"),
	Lebanon("Lebanon", "LB"),
	Saint_Lucia("Saint Lucia", "LC"),
	Liechtenstein("Liechtenstein", "LI"),
	Sri_Lanka("Sri Lanka", "LK"),
	Liberia("Liberia", "LR"),
	Lesotho("Lesotho", "LS"),
	Lithuania("Lithuania", "LT"),
	Luxembourg("Luxembourg", "LU"),
	Latvia("Latvia", "LV"),
	Libya("Libya", "LY"),
	Morocco("Morocco", "MA"),
	Monaco("Monaco", "MC"),
	Moldova("Moldova", "MD"),
	Montenegro("Montenegro", "ME"),
	Saint_Martin("Saint Martin", "MF"),
	Madagascar("Madagascar", "MG"),
	Marshall_Islands("Marshall Islands", "MH"),
	Macedonia("Macedonia", "MK"),
	Mali("Mali", "ML"),
	Myanmar("Myanmar", "MM"),
	Mongolia("Mongolia", "MN"),
	Macao("Macao", "MO"),
	Northern_Mariana_Islands("Northern Mariana Islands", "MP"),
	Martinique("Martinique", "MQ"),
	Mauritania("Mauritania", "MR"),
	Montserrat("Montserrat", "MS"),
	Malta("Malta", "MT"),
	Mauritius("Mauritius", "MU"),
	Maldives("Maldives", "MV"),
	Malawi("Malawi", "MW"),
	Mexico("Mexico", "MX"),
	Malaysia("Malaysia", "MY"),
	Mozambique("Mozambique", "MZ"),
	Namibia("Namibia", "NA"),
	New_Caledonia("New Caledonia", "NC"),
	Niger("Niger", "NE"),
	Norfolk_Island("Norfolk Island", "NF"),
	Nigeria("Nigeria", "NG"),
	Nicaragua("Nicaragua", "NI"),
	Netherlands("Netherlands", "NL"),
	Norway("Norway", "NO"),
	Nepal("Nepal", "NP"),
	Nauru("Nauru", "NR"),
	Niue("Niue", "NU"),
	New_Zealand("New Zealand", "NZ"),
	Oman("Oman", "OM"),
	Panama("Panama", "PA"),
	Peru("Peru", "PE"),
	French_Polynesia("French Polynesia", "PF"),
	Papua_New_Guinea("Papua New Guinea", "PG"),
	Philippines("Philippines", "PH"),
	Pakistan("Pakistan", "PK"),
	Poland("Poland", "PL"),
	Saint_Pierre_Miquelon("Saint Pierre and Miquelon", "PM"),
	Pitcairn("Pitcairn", "PN"),
	Puerto_Rico("Puerto Rico", "PR"),
	Palestinian_Territory("Palestinian Territory", "PS"),
	Portugal("Portugal", "PT"),
	Palau("Palau", "PW"),
	Paraguay("Paraguay", "PY"),
	Qatar("Qatar", "QA"),
	Reunion("Reunion", "RE"),
	Romania("Romania", "RO"),
	Serbia("Serbia", "RS"),
	Russia("Russia", "RU"),
	Rwanda("Rwanda", "RW"),
	Saudi_Arabia("Saudi Arabia", "SA"),
	Solomon_Islands("Solomon Islands", "SB"),
	Seychelles("Seychelles", "SC"),
	Sudan("Sudan", "SD"),
	South_Sudan("South Sudan", "SS"),
	Sweden("Sweden", "SE"),
	Singapore("Singapore", "SG"),
	Saint_Helena("Saint Helena", "SH"),
	Slovenia("Slovenia", "SI"),
	Svalbard_Jan_Mayen("Svalbard and Jan Mayen", "SJ"),
	Slovakia("Slovakia", "SK"),
	Sierra_Leone("Sierra Leone", "SL"),
	San_Marino("San Marino", "SM"),
	Senegal("Senegal", "SN"),
	Somalia("Somalia", "SO"),
	Suriname("Suriname", "SR"),
	Sao_Tome_Principe("Sao Tome and Principe", "ST"),
	Salvador("El Salvador", "SV"),
	Sint_Maarten("Sint Maarten", "SX"),
	Syria("Syria", "SY"),
	Swaziland("Swaziland", "SZ"),
	Turks_Caicos_Islands("Turks and Caicos Islands", "TC"),
	Chad("Chad", "TD"),
	French_Southern_Territories("French Southern Territories", "TF"),
	Togo("Togo", "TG"),
	Thailand("Thailand", "TH"),
	Tajikistan("Tajikistan", "TJ"),
	Tokelau("Tokelau", "TK"),
	East_Timor("East Timor", "TL"),
	Turkmenistan("Turkmenistan", "TM"),
	Tunisia("Tunisia", "TN"),
	Tonga("Tonga", "TO"),
	Turkey("Turkey", "TR"),
	Trinidad_Tobago("Trinidad and Tobago", "TT"),
	Tuvalu("Tuvalu", "TV"),
	Taiwan("Taiwan", "TW"),
	Tanzania("Tanzania", "TZ"),
	Ukraine("Ukraine", "UA"),
	Uganda("Uganda", "UG"),
	United_States_Minor_Outlying_Islands("United States Minor Outlying Islands", "UM"),
	United_States("United States", "US"),
	Uruguay("Uruguay", "UY"),
	Uzbekistan("Uzbekistan", "UZ"),
	Vatican("Vatican", "VA"),
	Saint_Vincent_Grenadines("Saint Vincent and the Grenadines", "VC"),
	Venezuela("Venezuela", "VE"),
	British_Virgin_Islands("British Virgin Islands", "VG"),
	US_Virgin_Islands("U.S. Virgin Islands", "VI"),
	Vietnam("Vietnam", "VN"),
	Vanuatu("Vanuatu", "VU"),
	Wallis_Futuna("Wallis and Futuna", "WF"),
	Samoa("Samoa", "WS"),
	Yemen("Yemen", "YE"),
	Mayotte("Mayotte", "YT"),
	South_Africa("South Africa", "ZA"),
	Zambia("Zambia", "ZM"),
	Zimbabwe("Zimbabwe", "ZW");

	private static Map<String, Country> byISOCodes = new HashMap<>();

	@JsonField(inputable = false)
	private final String name;

	@JsonField(inputable = false)
	private final String isoCode;

	@JsonField(handler = DoubleHandler.class, inputable = false)
	private final double latitude;

	@JsonField(handler = DoubleHandler.class, inputable = false)
	private final double longitude;

	@JsonField(handler = IntegerHandler.class, inputable = false)
	private final int altitude;

	private Country(String name, String isoCode) {
		this(name, isoCode, 0.0, 0.0, 0);
	}

	private Country(String name, String isoCode, double latitude, double longitude) {
		this(name, isoCode, longitude, latitude, 0);
	}

	private Country(String name, String isoCode, double latitude, double longitude, int altitude) {
		this.name = name;
		this.isoCode = isoCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public int getAltitude() {
		return this.altitude;
	}

	@Override
	public Earth getParentArea() {
		return Earth.instance;
	}

	/**
	 * @return the 2 letters ISO code of this country.
	 */
	public String getIsoCode() {
		return this.isoCode;
	}

	/**
	 *
	 * @param isoCode
	 *            a 2 letters ISO code
	 * @return the Country corresponding the given ISO code.
	 * @throws IllegalArgumentException
	 *             if the given ISO code is not registered.
	 */
	public static Country getByISOCode(String isoCode) {
		return Country.getByISOCode(isoCode, true);
	}

	/**
	 *
	 * @param isoCode
	 *            a 2 letters ISO code
	 * @param throwException
	 *            whether to throw an {@code IllegalArgumentException} if the
	 *            given code is invalid
	 * @return the Country corresponding the given ISO code, {@code null} if
	 *         none exists.
	 * @throws IllegalArgumentException
	 *             if the given ISO code is not registered and throwException is
	 *             {@code true}.
	 */
	public static Country getByISOCode(String isoCode, boolean throwException) {
		if (Country.byISOCodes.isEmpty()) {
			for (final Country c : Country.values()) {
				Country.byISOCodes.put(c.isoCode, c);
			}
			// FIXME Eclipse link does not support lambdas ...
			// Arrays.stream(Country.values()).parallel().forEach(c ->
			// byISOCodes.put(c.isoCode, c));
		}
		final String ic = isoCode.toUpperCase();
		final Country country = Country.byISOCodes.get(ic);
		if (country == null && throwException) {
			throw new IllegalArgumentException(isoCode + " is not a valid 2 letters ISO code");
		}
		return country;
	}

	@Override
	public JsonObject toJsonObject() {
		return JsonUtil.toJsonObject(this);
	}

}