package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer
{
   int retrycount = 0;
   int maxretrycount = 2;

   @Override
   public   boolean retry(ITestResult result)
   {
       if(retrycount < maxretrycount)
       {
           retrycount ++;
           return true;
       }
       return false;
   }

}
