package com.kgisl.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * To intercept and process requests before they are sent to servlets and response after servlet code is finished and 
 * before container sends the response back to the client. 
 */
public class HttpSecurityFilter implements Filter{
	
	/* No of allowed API hit per second */
	private Integer MAX_REQUESTS_PER_SECOND = 5;

	/* To declare cache builder to save IP address temporarily.
	 *  After defined seconds it will automatically clear the IP address from cache  */
	private LoadingCache<String, Integer> requestCountsPerIpAddress;

    public HttpSecurityFilter(){
        super();
        requestCountsPerIpAddress = CacheBuilder.newBuilder().
        		// after 1 second cache will get refreshed based on FIFO
                expireAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<String, Integer>() { 
            public Integer load(String key) {
                return 0;
            }
        });
    }

    /**
     * To intercept and process requests before they are sent to servlets to determine the request is from bot or not.
     */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		/* User agent info to find whether it is bot or actual service hit*/
		String userAgent = httpRequest.getHeader("User-Agent");
		
		/* If the user agent is null or in the defined list, it will won't allow the user agent to access the API */
		if(Objects.isNull(userAgent) || bots.stream().anyMatch(b -> userAgent.toLowerCase().contains(b.toLowerCase()))){
			httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		}else{
			  /* client IP address */
			String clientIpAddress = httpRequest.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr() : httpRequest.getHeader("X-FORWARDED-FOR");
			/* if the request from a specific IP is crossed the limit, it will send the TOO_MANY_REQUESTS status*/
	        if(isMaximumRequestsPerSecondExceeded(clientIpAddress)){
	        	httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
	         }else{
	        	 /* To proceed with actual request */
	        	 chain.doFilter(request, response);
	         }
		}
	}
	
	/**
	 * To find whether the given IP address hit count is crossed the defined limit. 
	 * 
	 * @param clientIpAddress - client IP address
	 * @return maximum exceed status
	 */
	private boolean isMaximumRequestsPerSecondExceeded(String clientIpAddress){
        Integer requests = 0;
        try {
            requests = requestCountsPerIpAddress.get(clientIpAddress);
            if(requests >= MAX_REQUESTS_PER_SECOND){
                requestCountsPerIpAddress.put(clientIpAddress, requests);
                return true;
             }
        } catch (ExecutionException e) {
            requests = 0;
        }
        requests++;
     // update IP with new request count
        requestCountsPerIpAddress.put(clientIpAddress, requests); 
        return false;
    }

	/* Possible crawlers */
	List<String> bots = Arrays.asList("bot","crawler","spider","80legs","baidu","yahoo! slurp","ia_archiver","mediapartners-google",
		    "lwp-trivial","nederland.zoek","ahoy","anthill","appie","arale","araneo","ariadne",            
		    "atn_worldwide","atomz","bjaaland","ukonline","calif","combine","cosmos","cusco",
		    "cyberspyder","digger","grabber","downloadexpress","ecollector","ebiness","esculapio",
		    "esther","felix ide","hamahakki","kit-fireball","fouineur","freecrawl","desertrealm",
		    "gcreep","golem","griffon","gromit","gulliver","gulper","whowhere","havindex","hotwired",
		    "htdig","ingrid","informant","inspectorwww","iron33","teoma","ask jeeves","jeeves",
		    "image.kapsi.net","kdd-explorer","label-grabber","larbin","linkidator","linkwalker",
		    "lockon","marvin","mattie","mediafox","merzscope","nec-meshexplorer","udmsearch","moget",
		    "motor","muncher","muninn","muscatferret","mwdsearch","sharp-info-agent","webmechanic",
		    "netscoop","newscan-online","objectssearch","orbsearch","packrat","pageboy","parasite",
		    "patric","pegasus","phpdig","piltdownman","pimptrain","plumtreewebaccessor","getterrobo-plus",
		    "raven","roadrunner","robbie","robocrawl","robofox","webbandit","scooter","search-au",
		    "searchprocess","senrigan","shagseeker","site valet","skymob","slurp","snooper","speedy",
		    "curl_image_client","suke","www.sygol.com","tach_bw","templeton","titin","topiclink","udmsearch",
		    "urlck","valkyrie libwww-perl","verticrawl","victoria","webscout","voyager","crawlpaper",
		    "webcatcher","t-h-u-n-d-e-r-s-t-o-n-e","webmoose","pagesinventory","webquest","webreaper",
		    "webwalker","winona","occam","robi","fdse","jobo","rhcs","gazz","dwcp","yeti","fido","wlm",
		    "wolp","wwwc","xget","legs","curl","webs","wget","sift","cmc",
		    
		    "googlebot","bingbot","yandexbot","ahrefsbot","msnbot","linkedinbot","exabot","compspybot",
		    "yesupbot","paperlibot","tweetmemebot","semrushbot","gigabot","voilabot","adsbot-google",
		    "botlink","alkalinebot","araybot","undrip bot","borg-bot","boxseabot","yodaobot","admedia bot",
		    "ezooms.bot","confuzzledbot","coolbot","internet cruiser robot","yolinkbot","diibot","musobot",
		    "dragonbot","elfinbot","wikiobot","twitterbot","contextad bot","hambot","iajabot","news bot",
		    "irobot","socialradarbot","ko_yappo_robot","skimbot","psbot","rixbot","seznambot","careerbot",
		    "simbot","solbot","mail.ru_bot","spiderbot","blekkobot","bitlybot","techbot","void-bot",
		    "vwbot_k","diffbot","friendfeedbot","archive.org_bot","woriobot","crystalsemanticsbot","wepbot",
		    "spbot","tweetedtimes bot","mj12bot","who.is bot","psbot","robot","jbot","bbot","bot",
		    
		    "baiduspider","80legs","baidu","yahoo! slurp","ia_archiver","mediapartners-google","lwp-trivial",
		    "nederland.zoek","ahoy","anthill","appie","arale","araneo","ariadne","atn_worldwide","atomz",
		    "bjaaland","ukonline","bspider","calif","christcrawler","combine","cosmos","cusco","cyberspyder",
		    "cydralspider","digger","grabber","downloadexpress","ecollector","ebiness","esculapio","esther",
		    "fastcrawler","felix ide","hamahakki","kit-fireball","fouineur","freecrawl","desertrealm",
		    "gammaspider","gcreep","golem","griffon","gromit","gulliver","gulper","whowhere","portalbspider",
		    "havindex","hotwired","htdig","ingrid","informant","infospiders","inspectorwww","iron33",
		    "jcrawler","teoma","ask jeeves","jeeves","image.kapsi.net","kdd-explorer","label-grabber",
		    "larbin","linkidator","linkwalker","lockon","logo_gif_crawler","marvin","mattie","mediafox",
		    "merzscope","nec-meshexplorer","mindcrawler","udmsearch","moget","motor","muncher","muninn",
		    "muscatferret","mwdsearch","sharp-info-agent","webmechanic","netscoop","newscan-online",
		    "objectssearch","orbsearch","packrat","pageboy","parasite","patric","pegasus","perlcrawler",
		    "phpdig","piltdownman","pimptrain","pjspider","plumtreewebaccessor","getterrobo-plus","raven",
		    "roadrunner","robbie","robocrawl","robofox","webbandit","scooter","search-au","searchprocess",
		    "senrigan","shagseeker","site valet","skymob","slcrawler","slurp","snooper","speedy",
		    "spider_monkey","spiderline","curl_image_client","suke","www.sygol.com","tach_bw","templeton",
		    "titin","topiclink","udmsearch","urlck","valkyrie libwww-perl","verticrawl","victoria",
		    "webscout","voyager","crawlpaper","wapspider","webcatcher","t-h-u-n-d-e-r-s-t-o-n-e",
		    "webmoose","pagesinventory","webquest","webreaper","webspider","webwalker","winona","occam",
		    "robi","fdse","jobo","rhcs","gazz","dwcp","yeti","crawler","fido","wlm","wolp","wwwc","xget",
		    "legs","curl","webs","wget","sift","cmc"/*, "Apache-HttpClient"*/);
}
