package pf.java.pfHelper;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import org.springframework.beans.factory.annotation.Autowired;

import pf.java.pfHelper.config.PFAppConfig;

public class PFCaching {

    //private static readonly String appKey = ConfigurationManager.AppSettings["AppKey"];
    @Autowired
    private static PFAppConfig _appConfig;
	private static Cache<String, Object> _cache;
	public PFCaching() {

        CachingProvider cachingProvider = Caching.getCachingProvider();
        
        CacheManager cacheManager = cachingProvider.getCacheManager();
         
        MutableConfiguration<String, Object> config = new MutableConfiguration();
         
        //Cache<String, String> cache = cacheManager.createCache("JDKCodeNames",config);
        _cache = cacheManager.createCache(_appConfig.getAppKey(),config);
	}
    public static Object Get(String key)
    {
    	return _cache.get(key);
        //return HttpContext.Current.Cache.Get(appKey + key);
    }

    public static void Set(String key, Object value)//, CacheDependency dependency)
    {

        if (value == null)//value为null时，Cache.Insert会报错
        {
            Remove(key);
        }
        else
        {
        	_cache.put(key, value);
            
            //HttpRuntime.Cache.Insert(appKey + key, value, dependency, DateTime.Now.AddDays(1), TimeSpan.Zero, CacheItemPriority.High, null);
        }
    }

    public static void Remove(String key)
    {
        if (_cache.containsKey(key))
        {
           _cache.remove(key);
        }
    }
}
