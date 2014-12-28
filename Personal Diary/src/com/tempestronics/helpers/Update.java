package com.tempestronics.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tempestronics.Launcher;

public class Update {

	private String sAppId;
	private String sVersion;
	private String sUrl;
	private String sFileName;
	private String sHash;
	private String sDescription;
	private String sLaunchArgs;
	
	public String getsAppId() {
		return sAppId;
	}

	public void setsAppId(String sAppId) {
		this.sAppId = sAppId;
	}

	public String getsVersion() {
		return sVersion;
	}

	public void setsVersion(String sVersion) {
		this.sVersion = sVersion;
	}

	public String getsUrl() {
		return sUrl;
	}

	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}

	public String getsFileName() {
		return sFileName;
	}

	public void setsFileName(String sFileName) {
		this.sFileName = sFileName;
	}

	public String getsHash() {
		return sHash;
	}

	public void setsHash(String sHash) {
		this.sHash = sHash;
	}

	public String getsDescription() {
		return sDescription;
	}

	public void setsDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	public String getsLaunchArgs() {
		return sLaunchArgs;
	}

	public void setsLaunchArgs(String sLaunchArgs) {
		this.sLaunchArgs = sLaunchArgs;
	}

	public String toString() {
		return "Version:" + this.getsVersion() + "\nURL:" + this.getsUrl()
		+ "\nFileName:" + this.getsFileName() + "\nHash:" + this.getsHash()
		+ "\nDescription:" + this.getsDescription() + "\nLaunchArgs:" + this.getsLaunchArgs();
	}
	
	public Update() throws IOException, ParseException {
		JSONObject json = (JSONObject) this.readJsonFromUrl(Launcher.UPDATE_LOCATION).get("update");
		this.sVersion = json.get("version").toString();
        this.sUrl = json.get("url").toString();
        this.sFileName = json.get("fileName").toString();
        this.sHash = json.get("hash").toString();
        this.sDescription = json.get("description").toString();
        this.sLaunchArgs = json.get("launchArgs").toString();
	}
	
	
	
	public JSONObject readJsonFromUrl(String url) throws IOException, ParseException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      JSONParser json = new JSONParser();
	      JSONObject obj = (JSONObject) json.parse(rd);
	      return obj;
	    } finally {
	      is.close();
	    }
	  }

	/**
	 * Compares two version strings. 
	 * Credits: http://stackoverflow.com/questions/6701948/efficient-way-to-compare-version-strings-in-java
	 * 
	 * Use this instead of String.compareTo() for a non-lexicographical 
	 * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
	 * 
	 * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
	 * 
	 * @param str1 a string of ordinal numbers separated by decimal points. 
	 * @param str2 a string of ordinal numbers separated by decimal points.
	 * @return The result is a negative integer if str1 is _numerically_ less than str2. 
	 *         The result is a positive integer if str1 is _numerically_ greater than str2. 
	 *         The result is zero if the strings are _numerically_ equal.
	 */
	public Integer versionCompare(String str1, String str2)
	{
	    String[] vals1 = str1.split("\\.");
	    String[] vals2 = str2.split("\\.");
	    int i = 0;
	    // set index to first non-equal ordinal or length of shortest version string
	    while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) 
	    {
	      i++;
	    }
	    // compare first non-equal ordinal number
	    if (i < vals1.length && i < vals2.length) 
	    {
	        int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
	        return Integer.signum(diff);
	    }
	    // the strings are equal or one string is a substring of the other
	    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
	    else
	    {
	        return Integer.signum(vals1.length - vals2.length);
	    }
	}

	public boolean isNewerVersionAvailable() {
		if(versionCompare(this.getsVersion(), Launcher.CURRENT_VERSION) > 0)
			return true;
		return false;
	}
}
