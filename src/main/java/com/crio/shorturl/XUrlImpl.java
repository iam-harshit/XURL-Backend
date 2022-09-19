package com.crio.shorturl;

import java.util.HashMap;
import java.util.Random;

public class XUrlImpl implements XUrl{

    HashMap<String, String> hm1 = new HashMap<>();
    HashMap<String, Integer> hm2 = new HashMap<>();

    String createShortUrl(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String randomString = "http://short.url/";
        int length = 9;

        Random rand = new Random();
        char[] shortUrl = new char[length];

        for(int i = 0; i < length; i++){
            shortUrl[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for(int i = 0; i < shortUrl.length; i++){
            randomString += shortUrl[i];
        }
        
        return randomString;
    }


    //If longUrl already has a corresponding shortUrl, return that shortUrl
    //If longUrl is new, create a new shortUrl for the longUrl and return it
    public String registerNewUrl(String longUrl){
        for(String lu : hm1.keySet()){
            if(lu.equals(longUrl)){
                return hm1.get(lu);
            }
        }

        String newString = createShortUrl();
        hm1.put(longUrl, newString);
        return newString;
    }

    //If shortUrl is already present, return null
    //Else register the specified shortUrl for the given longUrl
    public String registerNewUrl(String longUrl, String shortUrl){
        for(String url : hm1.values()){
            if(shortUrl.equals(url)){
                return null;
            }
        }

        hm1.put(longUrl, shortUrl);
        return shortUrl;
    }

    //If shortUrl doesn't have a corresponding longUrl, return null
    //Else return the corresponding longUrl
    public String getUrl(String shortUrl){
        for(String url : hm1.keySet()){
            if(shortUrl.equals(hm1.get(url))){
                hm2.put(url, hm2.getOrDefault(url, 0) + 1);
                return url;
            }
        }
        return null;
    }

    //Return the number of times the longUrl has been looked up using getUrl()
    @Override
    public Integer getHitCount(String longUrl) {
        if(hm2.containsKey(longUrl)){
            return hm2.get(longUrl);
        }
        return 0;
    }

    //Delete the mapping between this longUrl and its corresponding shortUrl
    //Do not zero the hit count for this longUrl
    public String delete(String longUrl){
        hm1.remove(longUrl);
        return longUrl;
    }

}