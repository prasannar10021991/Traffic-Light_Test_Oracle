package intersection;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class OracleDriver
{
	final static protected char EMPTY = 'E';
	  final static protected char CAR = 'C';
	
  public static int randomIndex(int min,int max){
	  return( min + (int)(Math.random() * ((max - min) + 1)));
  }

  protected static int ffff=0, ffnd=0, ffsd=0, ffbd=0, ff_f=0,ndnd=0,ndbd=0,ndf=0,sdsd=0,sdbd=0,sdf=0,bdbd=0,bdf=0,ff=0;
  protected static int sd=0,f_f=0,bd=0,f=0,nd=0;
  public static void main(String [] args) 
    throws IOException
  {
	  double failureProbability=0.0,cumulative_FP=0.0,total_fail=0.0;
	  int success=-1;
	  int runs,tick,failures=0;
	boolean failstate;
//14 possible states
	  
	  Scanner in = new Scanner(System.in);
    //create the input from a character buffer
	  //with 20%-C's and 80%-E's of the OPERATIONAL PROFILE
    char [] buf = new char [] 
	{'C', 'E', 'C', 'E', 'E', 'E', 'E', 'E', 'E', 'E'};
     // {'C', 'E', 'C', 'E', 'C', 'E', 'E', 'E', 'E', 'C'};

    PrintWriter out = 
      new PrintWriter(new OutputStreamWriter(System.out), true);
    out.println("Enter the no. of Runs(50/100/1000):");
    runs = in.nextInt();
    out.println("Enter the no. of Ticks(100/1000/10000):");
    tick = in.nextInt();
  
    
    int count = -1;
    TestOracle comparison;
    success=runs;
   //runs
    for(int j=0;j<runs;j++)
    {
    	failures=0;
    failstate=false;
    Intersection intersection = new Intersection();
    ArrayList<String> states = new ArrayList<String>();
    
    for (int i = 0; i < tick; i++) {
        	++count;
        	LightColour northLightA;
        	LightColour northLightB;
        	LightColour southLightA;
        	LightColour southLightB;
        	LightColour minorRoadLight;
        	//TestOracle prevLights = null;
        	
        	
          int index = OracleDriver.randomIndex(0, 9);
        	//int val = tick%10;
          intersection.tick(buf[index]);
          northLightA = intersection.getNorthLightA().getColour();
          northLightB = intersection.getNorthLightB().getColour();
          southLightA = intersection.getSouthLightA().getColour();
          southLightB = intersection.getSouthLightB().getColour();
          minorRoadLight = intersection.getMinorRoadLight().getColour();
          TestOracle checkLights = new TestOracle(northLightA,northLightB,southLightA,southLightB,minorRoadLight);
          
          //transition states
          
          
          if(northLightA!=LightColour.NONE&&northLightB!=LightColour.NONE
    			  &&southLightA!=LightColour.NONE&&southLightB!=LightColour.NONE
    			  &&minorRoadLight!=LightColour.NONE)
          {
        	  states.add("FF");
          }
          else
          {
        	  if(northLightA!=LightColour.NONE&&northLightB!=LightColour.NONE)
              {
            	  if(southLightA!=LightColour.NONE&&southLightB!=LightColour.NONE)
            	  {
            		  if(minorRoadLight==LightColour.NONE)
            		  {
            			  states.add("F");
            			  failstate=true;//ff ff nf
            		  }
            		  
            	  }
            	  else
            	  {
            		  if(southLightA!=LightColour.NONE||southLightB!=LightColour.NONE)
            		  {
            			  if(failstate!=true)
            			  {
            				  states.add("SD");  
            			  }
            			  else
            			  {
            				  states.add("F");
            			  }
            			    //ff nf ff
            		  }
            		  else
            		  {
            			  states.add("F"); //ff f ff
            			  failstate=true;
            		  }
            		  
            	  }
            	  
              }//sd
        	  else
        	  {
        		  if(northLightA!=LightColour.NONE||northLightB!=LightColour.NONE)
            	  {
            		  if(minorRoadLight==LightColour.NONE)
            		  {
            			  states.add("F");//f
            			  failstate=true;
            		  }
            		  else
            		  {
            			  if(southLightA!=LightColour.NONE&&southLightB!=LightColour.NONE)
                		  {
            				  if(failstate!=true)
            				  {
            					  states.add("ND");  
            				  }
                			  //nf ff ff
            				  else
            				  {
            					  states.add("F");
            					  failstate=true;
            				  }
                		  }
                		  else
                		  {
                			  if(southLightA!=LightColour.NONE||southLightB!=LightColour.NONE)
                    		  {
                				  if(failstate!=true)
                				  {
                					  states.add("BD");  
                				  }
                    			    //nf nf ff
                				  else
                				  {
                					  states.add("F");
                					  failstate=true;
                				  }
                    		  }
                    		  else
                    		  {
                    			  if(southLightA!=LightColour.NONE&&southLightB!=LightColour.NONE)
                    			  {
                    				  //states.add("ND");  //nf 
                    			  }
                    			  else
                    			  {
                    				  states.add("F");
                    				  failstate=true;//nf f ff
                    			  }
                    			   
                    		  } 
                		  }
            		  }
            		  
            	  }//nd bd
            	           	    
        	  }
        	  
        	  
        	  
          }//main if loop
             
          
                 
          
          
          if(count<10)
          {
        	  if(buf[index]==CAR)
              {
            	  if(checkLights.checkInitial(count))
            	  {
            		  count=0;
            	  }
            	       	  
              }
        	 
          }
          else
          {
        	  count=0;
          }
                
        
          
          comparison=checkLights.checkValid(buf[index], count);  
        
          if(comparison.valid)
          {
              String lightsAsString = "([" + northLightA + ", " + northLightB + "], " +
            			"[" + southLightA + ", " + southLightB + "], " +
            				minorRoadLight + ")";
            		      
            		      //out.print("tick(" + i + ") = ");
            		      //out.println("(" + buf[index] + ", " + lightsAsString);

          }
          else
          {
        	  failures++;
              String lightsAsString = "([" + northLightA + ", " + northLightB + "], " +
            			"[" + southLightA + ", " + southLightB + "], " +
            				minorRoadLight + ")";
            		      
            		    		      
            		    		  //out.print("tick(" + i + ") = ");
            		    	      //out.println("(" + buf[index] + ", " + lightsAsString+" /*Unsafe*/ ");

          }

        }//for ticks
    	
    	//transition probability
    if(states.size()>0)
    {
    	//out.println(states);
    	for (int k=0;k<states.size();k++)
    	{
    		if((k!=states.size()-1)&&states.get(k).equals(states.get(k+1)))
    		{
    			if(states.get(k).equals("FF"))
    			{
    				ffff++;
    				f_f++;
    			}
    			else if(states.get(k).equals("ND"))
    			{
    				ndnd++;
    				nd++;
    			}
    			else if(states.get(k).equals("SD"))
    			{
    				sdsd++;
    				sd++;
    			}
    			else if(states.get(k).equals("BD"))
    			{
    				bdbd++;
    				bd++;
    			}
    			else if(states.get(k).equals("F"))
    			{
    				ff++;
    				f++;
    				
    			}
    		}
    		else
    		{
    			if(k!=states.size()-1)
    			{
    				if(states.get(k).equals("FF")&&states.get(k+1).equals("ND"))
        			{
        				ffnd++;
        				f_f++;
        			}
        			else if(states.get(k).equals("FF")&&states.get(k+1).equals("SD"))
        			{
        				ffsd++;
        				f_f++;
        			}
        			else if(states.get(k).equals("FF")&&states.get(k+1).equals("BD"))
        			{
        				ffbd++;
        				f_f++;
        			}
        			else if(states.get(k).equals("FF")&&states.get(k+1).equals("F"))
        			{
        				ff_f++;
        				f_f++;
        			}
        			else if(states.get(k).equals("ND")&&states.get(k+1).equals("BD"))
        			{
        				ndbd++;
        				nd++;
        			}
        			else if(states.get(k).equals("ND")&&states.get(k+1).equals("F"))
        			{
        				ndf++;
        				nd++;
        			}
        			else if(states.get(k).equals("SD")&&states.get(k+1).equals("BD"))
        			{
        				sdbd++;
        				sd++;
        			}
        			else if(states.get(k).equals("SD")&&states.get(k+1).equals("F"))
        			{
        				sdf++;
        				sd++;
        			}
        			else if(states.get(k).equals("BD")&&states.get(k+1).equals("F"))
        			{
        				bdf++;
        				bd++;
        			}
        	
    			}
    					
    		}
    	}
    	
    	    
    }
    double ffffp = (double)ffff/f_f;
    ffffp = Math.round(ffffp * 100.0) / 100.0;
    double ffndp = (double)ffnd/f_f;
    ffndp=Math.round(ffndp * 100.0) / 100.0;
    double ffsdp = (double)ffsd/f_f;
    ffsdp=Math.round(ffsdp * 100.0) / 100.0;
    double ffbdp = (double)ffbd/f_f;
    ffbdp=Math.round(ffbdp * 100.0) / 100.0;
    double ff_fp = (double)ff_f/f_f;
    ff_fp=Math.round(ff_fp * 100.0) / 100.0;
    double ndndp = (double)ndnd/nd;
    ndndp = Math.round(ndndp * 100.0) / 100.0;
    double ndbdp = (double)ndbd/nd;
    ndbdp=Math.round(ndbdp * 100.0) / 100.0;
    double ndfp = (double)ndf/nd;
    ndfp=Math.round(ndfp * 100.0) / 100.0;
    double sdsdp = (double)sdsd/sd;
    sdsdp= Math.round(sdsdp * 100.0) / 100.0;
    double sdbdp = (double)sdbd/sd;
    sdbdp=Math.round(sdbdp * 100.0) / 100.0;
    double sdfp = (double)sdf/sd;
    sdfp=Math.round(sdfp * 100.0) / 100.0;
    double bdbdp = (double)bdbd/bd;
    bdbdp=Math.round(bdbdp * 100.0) / 100.0;
    double bdfp = (double)bdf/bd;
    bdfp=Math.round(bdfp * 100.0) / 100.0;
    double ffp = (double)ff/f;
    ffp=Math.round(ffp * 100.0) / 100.0;
    double sum = OracleDriver.add(ffffp,ffndp,ffsdp,ffbdp,ff_fp);
    double dd = Math.round((sum)*10000.0)/100.0;
    out.println();
    out.println("Transition Probabilities for different states are :");
    out.println("FF-FF: "+ffffp+", FF-ND: "+ffndp+", FF-SD: "+ffsdp+", FF-BD: "+ffbdp+", FF-F: "+ff_fp+"\n"+"ND-ND: "+ndndp+", ND-BD: "+ndbdp+", ND-F: "+ndfp+"\n"+"SD-SD: "+sdsdp+", SD-BD: "+sdbdp+", SD-F: "+sdfp+"\n"+"BD-BD: "+bdbdp+", BD-F: "+bdfp+"\n"+"F-F: "+ffp);
    	//cumulative failure prob
    out.println();	
    	failureProbability = ((double)failures/tick);
    	if(failureProbability==0.0)
    	{
    		//out.print(success);
    		--success;
    	}
    	//out.println("failure probability:"+failureProbability);
    	total_fail=total_fail+ failureProbability;
    }//for runs
    
    cumulative_FP = (((double)success/runs)*100);
    out.println("Cumulative Failure Probabiliity of "+tick+" ticks on "+runs+" runs: "+(cumulative_FP));    
    out.close();
 
  }
  
  public static double add(double d1,double d2,double d3,double d4,double d5)
  {
	  double values= d1+d2+d3+d4+d5;
	  return values;
  }
}
