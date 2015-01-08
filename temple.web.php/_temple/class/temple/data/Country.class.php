<?php

namespace temple\data ;

/**
 * Description of Country
 *
 * @author florent
 */
final class Country {

	use \temple\Enum;

	public static $AFGHANISTAN; // 0
	public static $ALAND_ISLANDS; // 1
	public static $ALBANIA; // 2
	public static $ALGERIA; // 3
	public static $AMERICAN_SAMOA; // 4
	public static $ANDORRA; // 5
	public static $ANGOLA; // 6
	public static $ANGUILLA; // 7
	public static $ANTARCTICA; // 8
	public static $ANTIGUA_AND_BARBUDA; // 9
	public static $ARGENTINA; // 10
	public static $ARMENIA; // 11
	public static $ARUBA; // 12
	public static $AUSTRALIA; // 13
	public static $AUSTRIA; // 14
	public static $AZERBAIJAN; // 15
	public static $BAHAMAS; // 16
	public static $BAHRAIN; // 17
	public static $BANGLADESH; // 18
	public static $BARBADOS; // 19
	public static $BELARUS; // 20
	public static $BELGIUM; // 21
	public static $BELIZE; // 22
	public static $BENIN; // 23
	public static $BERMUDA; // 24
	public static $BHUTAN; // 25
	public static $BOLIVIA; // 26
	public static $BONAIRE; // 27
	public static $BOSNIA_AND_HERZEGOVINA; // 28
	public static $BOTSWANA; // 29
	public static $BOUVET_ISLAND; // 30 - TO FILTER OUT
	public static $BRAZIL; // 31
	public static $BRITISH_INDIAN_OCEAN_TERRITORY; // 32
	public static $BRUNEI_DARUSSALAM; // 33
	public static $BULGARIA; // 34
	public static $BURKINA_FASO; // 35
	public static $BURUNDI; // 36
	public static $CAMBODIA; // 37
	public static $CAMEROON; // 38
	public static $CANADA; // 39
	public static $CAPE_VERDE; // 40
	public static $CAYMAN_ISLANDS; // 41
	public static $CENTRAL_AFRICAN_REPUBLIC; // 42
	public static $CHAD; // 43
	public static $CHILE; // 44
	public static $CHINA; // 45
	public static $CHRISTMAS_ISLAND; // 46
	public static $COCOS_KEELING_ISLANDS; // 47
	public static $COLOMBIA; // 48
	public static $COMOROS; // 49
	public static $CONGO; // 50
	public static $CONGO_; // 51
	public static $COOK_ISLANDS; // 52
	public static $COSTA_RICA; // 53
	public static $COTE_DIVOIRE; // 54
	public static $CROATIA; // 55
	public static $CUBA; // 56
	public static $CURACAO; // 57
	public static $CYPRUS; // 58
	public static $CZECH_REPUBLIC; // 59
	public static $DENMARK; // 60
	public static $DJIBOUTI; // 61
	public static $DOMINICA; // 62
	public static $DOMINICAN_REPUBLIC; // 63
	public static $ECUADOR; // 64
	public static $EGYPT; // 65
	public static $EL_SALVADOR; // 66
	public static $EQUATORIAL_GUINEA; // 67
	public static $ERITREA; // 68
	public static $ESTONIA; // 69
	public static $ETHIOPIA; // 70
	public static $FALKLAND_ISLANDS_MALVINAS; // 71
	public static $FAROE_ISLANDS; // 72
	public static $FIJI; // 73
	public static $FINLAND; // 74
	public static $FRANCE; // 75
	public static $FRENCH_GUIANA; // 76
	public static $FRENCH_POLYNESIA; // 77
	public static $FRENCH_SOUTHERN_TERRITORIES; // 78
	public static $GABON; // 79
	public static $GAMBIA; // 80
	public static $GEORGIA; // 81
	public static $GERMANY; // 82
	public static $GHANA; // 83
	public static $GIBRALTAR; // 84
	public static $GREECE; // 85
	public static $GREENLAND; // 86
	public static $GRENADA; // 87
	public static $GUADELOUPE; // 88
	public static $GUAM; // 89
	public static $GUATEMALA; // 90
	public static $GUERNSEY; // 91
	public static $GUINEA; // 92
	public static $GUINEA_BISSAU; // 93
	public static $GUYANA; // 94
	public static $HAITI; // 95
	public static $HEARD_ISLAND_AND_MCDONALD_ISLANDS; // 96
	public static $HOLY_SEE_VATICAN_CITY_STATE; // 97
	public static $HONDURAS; // 98
	public static $HONG_KONG; // 99
	public static $HUNGARY; // 100
	public static $ICELAND; // 101
	public static $INDIA; // 102
	public static $INDONESIA; // 103
	public static $IRAN; // 104
	public static $IRAQ; // 105
	public static $IRELAND; // 106
	public static $ISLE_OF_MAN; // 107
	public static $ISRAEL; // 108
	public static $ITALY; // 109
	public static $JAMAICA; // 110
	public static $JAPAN; // 111
	public static $JERSEY; // 112
	public static $JORDAN; // 113
	public static $KAZAKHSTAN; // 114
	public static $KENYA; // 115
	public static $KIRIBATI; // 116
	public static $KOREA; // 117
	public static $KOREA_; // 118
	public static $KUWAIT; // 119
	public static $KYRGYZSTAN; // 120
	public static $LAO_PEOPLES_DEMOCRATIC_REPUBLIC; // 121
	public static $LATVIA; // 122
	public static $LEBANON; // 123
	public static $LESOTHO; // 124
	public static $LIBERIA; // 125
	public static $LIBYA; // 126
	public static $LIECHTENSTEIN; // 127
	public static $LITHUANIA; // 128
	public static $LUXEMBOURG; // 129
	public static $MACAO; // 130
	public static $MACEDONIA; // 131
	public static $MADAGASCAR; // 132
	public static $MALAWI; // 133
	public static $MALAYSIA; // 134
	public static $MALDIVES; // 135
	public static $MALI; // 136
	public static $MALTA; // 137
	public static $MARSHALL_ISLANDS; // 138
	public static $MARTINIQUE; // 139
	public static $MAURITANIA; // 140
	public static $MAURITIUS; // 141
	public static $MAYOTTE; // 142
	public static $MEXICO; // 143
	public static $MICRONESIA; // 144
	public static $MOLDOVA; // 145
	public static $MONACO; // 146
	public static $MONGOLIA; // 147
	public static $MONTENEGRO; // 148
	public static $MONTSERRAT; // 149
	public static $MOROCCO; // 150
	public static $MOZAMBIQUE; // 151
	public static $MYANMAR; // 152
	public static $NAMIBIA; // 153
	public static $NAURU; // 154
	public static $NEPAL; // 155
	public static $NETHERLANDS; // 156
	public static $NEW_CALEDONIA; // 157
	public static $NEW_ZEALAND; // 158
	public static $NICARAGUA; // 159
	public static $NIGER; // 160
	public static $NIGERIA; // 161
	public static $NIUE; // 162
	public static $NORFOLK_ISLAND; // 163
	public static $NORTHERN_MARIANA_ISLANDS; // 164
	public static $NORWAY; // 165
	public static $OMAN; // 166
	public static $PAKISTAN; // 167
	public static $PALAU; // 168
	public static $PALESTINE; // 169
	public static $PANAMA; // 170
	public static $PAPUA_NEW_GUINEA; // 171
	public static $PARAGUAY; // 172
	public static $PERU; // 173
	public static $PHILIPPINES; // 174
	public static $PITCAIRN; // 175
	public static $POLAND; // 176
	public static $PORTUGAL; // 177
	public static $PUERTO_RICO; // 178
	public static $QATAR; // 179
	public static $REUNION; // 180
	public static $ROMANIA; // 181
	public static $RUSSIAN_FEDERATION; // 182
	public static $RWANDA; // 183
	public static $SAINT_BARTHELEMY; // 184
	public static $SAINT_HELENA; // 185
	public static $SAINT_KITTS_AND_NEVIS; // 186
	public static $SAINT_LUCIA; // 187
	public static $SAINT_MARTIN_FRENCH_PART; // 188
	public static $SAINT_PIERRE_AND_MIQUELON; // 189
	public static $SAINT_VINCENT_AND_THE_GRENADINES; // 190
	public static $SAMOA; // 191
	public static $SAN_MARINO; // 192
	public static $SAO_TOME_AND_PRINCIPE; // 193
	public static $SAUDI_ARABIA; // 194
	public static $SENEGAL; // 195
	public static $SERBIA; // 196
	public static $SEYCHELLES; // 197
	public static $SIERRA_LEONE; // 198
	public static $SINGAPORE; // 199
	public static $SINT_MAARTEN_DUTCH_PART; // 200
	public static $SLOVAKIA; // 201
	public static $SLOVENIA; // 202
	public static $SOLOMON_ISLANDS; // 203
	public static $SOMALIA; // 204
	public static $SOUTH_AFRICA; // 205
	public static $SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS; // 206
	public static $SOUTH_SUDAN; // 207
	public static $SPAIN; // 208
	public static $SRI_LANKA; // 209
	public static $SUDAN; // 210
	public static $SURINAME; // 211
	public static $SVALBARD_AND_JAN_MAYEN; // 212
	public static $SWAZILAND; // 213
	public static $SWEDEN; // 214
	public static $SWITZERLAND; // 215
	public static $SYRIAN_ARAB_REPUBLIC; // 216
	public static $TAIWAN; // 217
	public static $TAJIKISTAN; // 218
	public static $TANZANIA; // 219
	public static $THAILAND; // 220
	public static $TIMOR_LESTE; // 221
	public static $TOGO; // 222
	public static $TOKELAU; // 223
	public static $TONGA; // 224
	public static $TRINIDAD_AND_TOBAGO; // 225
	public static $TUNISIA; // 226
	public static $TURKEY; // 227
	public static $TURKMENISTAN; // 228
	public static $TURKS_AND_CAICOS_ISLANDS; // 229
	public static $TUVALU; // 230
	public static $UGANDA; // 231
	public static $UKRAINE; // 232
	public static $UNITED_ARAB_EMIRATES; // 233
	public static $UNITED_KINGDOM; // 234
	public static $UNITED_STATES; // 235
	public static $UNITED_STATES_MINOR_OUTLYING_ISLANDS; // 236
	public static $URUGUAY; // 237
	public static $UZBEKISTAN; // 238
	public static $VANUATU; // 239
	public static $VENEZUELA; // 240
	public static $VIET_NAM; // 241
	public static $VIRGIN_ISLANDS; // 242
	public static $VIRGIN_ISLANDS_; // 243
	public static $WALLIS_AND_FUTUNA; // 244
	public static $WESTERN_SAHARA; // 245
	public static $YEMEN; // 246
	public static $ZAMBIA; // 247
	public static $ZIMBABWE; // 248 
	
	private static $strings = ['Afghanistan', '&Aring;land Islands', 'Albania', 'Algeria', 'American Samoa', 
		'Andorra', 'Angola', 'Anguilla', 'Antarctica', 'Antigua and Barbuda', 'Argentina', 'Armenia', 'Aruba', 
		'Australia', 'Austria', 'Azerbaijan', 'Bahamas', 'Bahrain', 'Bangladesh', 'Barbados', 'Belarus', 'Belgium', 
		'Belize', 'Benin', 'Bermuda', 'Bhutan', 'Bolivia, Plurinational State of', 
		'Bonaire, Sint Eustatius and Saba', 'Bosnia and Herzegovina', 'Botswana', 'Bouvet Island', 'Brazil', 
		'British Indian Ocean Territory', 'Brunei Darussalam', 'Bulgaria', 'Burkina Faso', 'Burundi', 
		'Cambodia', 'Cameroon', 'Canada', 'Cape Verde', 'Cayman Islands', 'Central African Republic', 'Chad', 
		'Chile', 'China', 'Christmas Island', 'Cocos (Keeling) Islands', 'Colombia', 'Comoros', 'Congo', 
		'Congo, the Democratic Republic of the', 'Cook Islands', 'Costa Rica', 'C&ocirc;te d&#39;Ivoire', 
		'Croatia', 'Cuba', 'Cura&ccedil;ao', 'Cyprus', 'Czech Republic', 'Denmark', 'Djibouti', 'Dominica', 
		'Dominican Republic', 'Ecuador', 'Egypt', 'El Salvador', 'Equatorial Guinea', 'Eritrea', 'Estonia', 
		'Ethiopia', 'Falkland Islands (Malvinas)', 'Faroe Islands', 'Fiji', 'Finland', 'France', 'French Guiana', 
		'French Polynesia', 'French Southern Territories', 'Gabon', 'Gambia', 'Georgia', 'Germany', 'Ghana', 
		'Gibraltar', 'Greece', 'Greenland', 'Grenada', 'Guadeloupe', 'Guam', 'Guatemala', 'Guernsey', 'Guinea', 
		'Guinea-Bissau', 'Guyana', 'Haiti', 'Heard Island and McDonald Islands', 'Holy See (Vatican City State)', 
		'Honduras', 'Hong Kong', 'Hungary', 'Iceland', 'India', 'Indonesia', 'Iran, Islamic Republic of', 'Iraq', 
		'Ireland', 'Isle of Man', 'Israel', 'Italy', 'Jamaica', 'Japan', 'Jersey', 'Jordan', 'Kazakhstan', 'Kenya', 
		'Kiribati', 'Korea, Democratic People&#39;s Republic of', 'Korea, Republic of', 'Kuwait', 'Kyrgyzstan', 
		'Lao People&#39;s Democratic Republic', 'Latvia', 'Lebanon', 'Lesotho', 'Liberia', 'Libya', 'Liechtenstein', 
		'Lithuania', 'Luxembourg', 'Macao', 'Macedonia, the former Yugoslav Republic of', 'Madagascar', 'Malawi', 
		'Malaysia', 'Maldives', 'Mali', 'Malta', 'Marshall Islands', 'Martinique', 'Mauritania', 'Mauritius', 
		'Mayotte', 'Mexico', 'Micronesia, Federated States of', 'Moldova, Republic of', 'Monaco', 'Mongolia', 
		'Montenegro', 'Montserrat', 'Morocco', 'Mozambique', 'Myanmar', 'Namibia', 'Nauru', 'Nepal', 'Netherlands', 
		'New Caledonia', 'New Zealand', 'Nicaragua', 'Niger', 'Nigeria', 'Niue', 'Norfolk Island', 
		'Northern Mariana Islands', 'Norway', 'Oman', 'Pakistan', 'Palau', 'Palestine, State of', 'Panama', 
		'Papua New Guinea', 'Paraguay', 'Peru', 'Philippines', 'Pitcairn', 'Poland', 'Portugal', 'Puerto Rico', 
		'Qatar', 'R&eacute;union', 'Romania', 'Russian Federation', 'Rwanda', 'Saint Barth&eacute;lemy', 
		'Saint Helena, Ascension and Tristan da Cunha', 'Saint Kitts and Nevis', 'Saint Lucia', 
		'Saint Martin (French part)', 'Saint Pierre and Miquelon', 'Saint Vincent and the Grenadines', 'Samoa', 
		'San Marino', 'Sao Tome and Principe', 'Saudi Arabia', 'Senegal', 'Serbia', 'Seychelles', 'Sierra Leone', 
		'Singapore', 'Sint Maarten (Dutch part)', 'Slovakia', 'Slovenia', 'Solomon Islands', 'Somalia', 
		'South Africa', 'South Georgia and the South Sandwich Islands', 'South Sudan', 'Spain', 'Sri Lanka', 
		'Sudan', 'Suriname', 'Svalbard and Jan Mayen', 'Swaziland', 'Sweden', 'Switzerland', 'Syrian Arab Republic', 
		'Taiwan, Province of China', 'Tajikistan', 'Tanzania, United Republic of', 'Thailand', 'Timor-Leste', 
		'Togo', 'Tokelau', 'Tonga', 'Trinidad and Tobago', 'Tunisia', 'Turkey', 'Turkmenistan', 
		'Turks and Caicos Islands', 'Tuvalu', 'Uganda', 'Ukraine', 'United Arab Emirates', 'United Kingdom', 
		'United States', 'United States Minor Outlying Islands', 'Uruguay', 'Uzbekistan', 'Vanuatu', 
		'Venezuela, Bolivarian Republic of', 'Viet Nam', 'Virgin Islands, British', 'Virgin Islands, U.S.', 
		'Wallis and Futuna', 'Western Sahara', 'Yemen', 'Zambia', 'Zimbabwe'];
	
	private static $isoCodes2 = ['AF', 'AX', 'AL', 'DZ', 'AS', 'AD', 'AO', 'AI', 'AQ', 'AG', 'AR', 'AM', 'AW', 'AU', 
		'AT', 'AZ', 'BS', 'BH', 'BD', 'BB', 'BY', 'BE', 'BZ', 'BJ', 'BM', 'BT', 'BO', 'BQ', 'BA', 'BW', 'BV', 'BR', 
		'IO', 'BN', 'BG', 'BF', 'BI', 'KH', 'CM', 'CA', 'CV', 'KY', 'CF', 'TD', 'CL', 'CN', 'CX', 'CC', 'CO', 'KM', 
		'CG', 'CD', 'CK', 'CR', 'CI', 'HR', 'CU', 'CW', 'CY', 'CZ', 'DK', 'DJ', 'DM', 'DO', 'EC', 'EG', 'SV', 'GQ', 
		'ER', 'EE', 'ET', 'FK', 'FO', 'FJ', 'FI', 'FR', 'GF', 'PF', 'TF', 'GA', 'GM', 'GE', 'DE', 'GH', 'GI', 'GR', 
		'GL', 'GD', 'GP', 'GU', 'GT', 'GG', 'GN', 'GW', 'GY', 'HT', 'HM', 'VA', 'HN', 'HK', 'HU', 'IS', 'IN', 'ID', 
		'IR', 'IQ', 'IE', 'IM', 'IL', 'IT', 'JM', 'JP', 'JE', 'JO', 'KZ', 'KE', 'KI', 'KP', 'KR', 'KW', 'KG', 'LA', 
		'LV', 'LB', 'LS', 'LR', 'LY', 'LI', 'LT', 'LU', 'MO', 'MK', 'MG', 'MW', 'MY', 'MV', 'ML', 'MT', 'MH', 'MQ', 
		'MR', 'MU', 'YT', 'MX', 'FM', 'MD', 'MC', 'MN', 'ME', 'MS', 'MA', 'MZ', 'MM', 'NA', 'NR', 'NP', 'NL', 'NC', 
		'NZ', 'NI', 'NE', 'NG', 'NU', 'NF', 'MP', 'NO', 'OM', 'PK', 'PW', 'PS', 'PA', 'PG', 'PY', 'PE', 'PH', 'PN', 
		'PL', 'PT', 'PR', 'QA', 'RE', 'RO', 'RU', 'RW', 'BL', 'SH', 'KN', 'LC', 'MF', 'PM', 'VC', 'WS', 'SM', 'ST', 
		'SA', 'SN', 'RS', 'SC', 'SL', 'SG', 'SX', 'SK', 'SI', 'SB', 'SO', 'ZA', 'GS', 'SS', 'ES', 'LK', 'SD', 'SR', 
		'SJ', 'SZ', 'SE', 'CH', 'SY', 'TW', 'TJ', 'TZ', 'TH', 'TL', 'TG', 'TK', 'TO', 'TT', 'TN', 'TR', 'TM', 'TC', 
		'TV', 'UG', 'UA', 'AE', 'GB', 'US', 'UM', 'UY', 'UZ', 'VU', 'VE', 'VN', 'VG', 'VI', 'WF', 'EH', 'YE', 'ZM', 
		'ZW'];
	
	private static $isoCodes3 = ['AFG', 'ALA', 'ALB', 'DZA', 'ASM', 'AND', 'AGO', 'AIA', 'ATA', 'ATG', 'ARG', 'ARM', 
		'ABW', 'AUS', 'AUT', 'AZE', 'BHS', 'BHR', 'BGD', 'BRB', 'BLR', 'BEL', 'BLZ', 'BEN', 'BMU', 'BTN', 'BOL', 
		'BES', 'BIH', 'BWA', 'BVT', 'BRA', 'IOT', 'BRN', 'BGR', 'BFA', 'BDI', 'KHM', 'CMR', 'CAN', 'CPV', 'CYM', 
		'CAF', 'TCD', 'CHL', 'CHN', 'CXR', 'CCK', 'COL', 'COM', 'COG', 'COD', 'COK', 'CRI', 'CIV', 'HRV', 'CUB', 
		'CUW', 'CYP', 'CZE', 'DNK', 'DJI', 'DMA', 'DOM', 'ECU', 'EGY', 'SLV', 'GNQ', 'ERI', 'EST', 'ETH', 'FLK', 
		'FRO', 'FJI', 'FIN', 'FRA', 'GUF', 'PYF', 'ATF', 'GAB', 'GMB', 'GEO', 'DEU', 'GHA', 'GIB', 'GRC', 'GRL', 
		'GRD', 'GLP', 'GUM', 'GTM', 'GGY', 'GIN', 'GNB', 'GUY', 'HTI', 'HMD', 'VAT', 'HND', 'HKG', 'HUN', 'ISL', 
		'IND', 'IDN', 'IRN', 'IRQ', 'IRL', 'IMN', 'ISR', 'ITA', 'JAM', 'JPN', 'JEY', 'JOR', 'KAZ', 'KEN', 'KIR', 
		'PRK', 'KOR', 'KWT', 'KGZ', 'LAO', 'LVA', 'LBN', 'LSO', 'LBR', 'LBY', 'LIE', 'LTU', 'LUX', 'MAC', 'MKD', 
		'MDG', 'MWI', 'MYS', 'MDV', 'MLI', 'MLT', 'MHL', 'MTQ', 'MRT', 'MUS', 'MYT', 'MEX', 'FSM', 'MDA', 'MCO', 
		'MNG', 'MNE', 'MSR', 'MAR', 'MOZ', 'MMR', 'NAM', 'NRU', 'NPL', 'NLD', 'NCL', 'NZL', 'NIC', 'NER', 'NGA', 
		'NIU', 'NFK', 'MNP', 'NOR', 'OMN', 'PAK', 'PLW', 'PSE', 'PAN', 'PNG', 'PRY', 'PER', 'PHL', 'PCN', 'POL', 
		'PRT', 'PRI', 'QAT', 'REU', 'ROU', 'RUS', 'RWA', 'BLM', 'SHN', 'KNA', 'LCA', 'MAF', 'SPM', 'VCT', 'WSM', 
		'SMR', 'STP', 'SAU', 'SEN', 'SRB', 'SYC', 'SLE', 'SGP', 'SXM', 'SVK', 'SVN', 'SLB', 'SOM', 'ZAF', 'SGS', 
		'SSD', 'ESP', 'LKA', 'SDN', 'SUR', 'SJM', 'SWZ', 'SWE', 'CHE', 'SYR', 'TWN', 'TJK', 'TZA', 'THA', 'TLS', 
		'TGO', 'TKL', 'TON', 'TTO', 'TUN', 'TUR', 'TKM', 'TCA', 'TUV', 'UGA', 'UKR', 'ARE', 'GBR', 'USA', 'UMI', 
		'URY', 'UZB', 'VUT', 'VEN', 'VNM', 'VGB', 'VIR', 'WLF', 'ESH', 'YEM', 'ZMB', 'ZWE'];

	public function __toString() {
		return self::$strings[$this->getOrdinal()] ;
	}
	
}

