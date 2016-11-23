package intersection;




public class TestOracle {
	  final static protected char EMPTY = 'E';
	  final static protected char CAR = 'C';
		LightColour nA_,nB_,sA_,sB_,m_;
		protected boolean valid ;
	  public TestOracle(LightColour nA,LightColour nB,LightColour sA,LightColour sB,LightColour m){
			this.nA_=nA;
			this.nB_=nB;
			this.sA_=sA;
			this.sB_=sB;
			this.m_ = m;
			
		}
	  public boolean checkInitial(int count)
	  {
		  if((this.nA_==LightColour.GREEN||this.nB_==LightColour.GREEN)&&(this.sA_==LightColour.GREEN||this.sB_==LightColour.GREEN)&&(this.m_==LightColour.RED))//greengreen green green red
			{
				
				return true;
			}
			else
			{
				if(count==0||count==9)
				{
					if((this.nA_==LightColour.NONE&&this.nB_==LightColour.NONE)||(this.sA_==LightColour.GREEN||this.sB_==LightColour.GREEN))
					{
						return true;
					}
					else if((this.nA_==LightColour.GREEN||this.nB_==LightColour.GREEN)||(this.sA_==LightColour.NONE&&this.sB_==LightColour.NONE))
					{
						return true;
					}
					else if(this.m_==LightColour.NONE)
					{
						return true;
					}
				}
				else
				{
					return false;
				}
				
				
			}
		return false;

	  }
	  public TestOracle(LightColour nA,LightColour nB,LightColour sA,LightColour sB,LightColour m,boolean check){
			this.nA_=nA;
			this.nB_=nB;
			this.sA_=sA;
			this.sB_=sB;
			this.m_ = m;
			this.valid = check;
			
		}
	  public LightColour getnorthA()
	  {
		  return this.nA_;
	  }
	  public LightColour getnorthB()
	  {
		  return this.nB_;
	  }
	  public LightColour getsouthA()
	  {
		  return this.sA_;
	  }
	  public LightColour getsouthB()
	  {
		  return this.sB_;
	  }
	  public LightColour getMinor()
	  {
		  return this.m_;
	  }
	  public boolean checkEqual(LightColour nA,LightColour nB,LightColour sA,LightColour sB,LightColour m)
	  {
		  if(this.nA_==nA&&this.nB_==nB&&this.sA_==sA&&this.sB_==sB&&this.m_==m)
		  {
			  return true;
		  }
		  else
		  {
			  return false;
			  
		  }
	  }
	public TestOracle checkValid(char c,int tick){
		TestOracle result = new TestOracle(LightColour.NONE,LightColour.NONE,LightColour.NONE,LightColour.NONE,LightColour.NONE,false);
		if(c == CAR)
		{
			if(tick==0||tick==9)
			{
				if(this.nA_==LightColour.GREEN&&this.nB_==LightColour.GREEN&&this.sA_==LightColour.GREEN&&this.sB_==LightColour.GREEN&&this.m_==LightColour.RED)//greengreen green green red
				{
					
					result =   new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
					return result;
				}
				else
				{
					if(this.m_==LightColour.RED)
					{
						if((this.nA_==LightColour.GREEN||this.nB_==LightColour.GREEN)&&(this.sA_==LightColour.GREEN||this.sB_==LightColour.GREEN))
						{
							result =   new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
							return result;
						}
						else
						{
							result =  new TestOracle(LightColour.GREEN,LightColour.GREEN,LightColour.GREEN,LightColour.GREEN,LightColour.RED,false);
							return result;
						}
					}
					else
					{
						result =  new TestOracle(LightColour.GREEN,LightColour.GREEN,LightColour.GREEN,LightColour.GREEN,LightColour.RED,false);
						return result;
					}
					
					
				}
			}
			else if(tick>0&&tick<3)
			{
				if(this.nA_==LightColour.AMBER&&this.nB_==LightColour.AMBER&&this.sA_==LightColour.AMBER&&this.sB_==LightColour.AMBER&&this.m_==LightColour.RED)
				{
					result = new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
					return result;
				}
				else
				{
					if(this.m_==LightColour.RED)
					{
						if((this.nA_==LightColour.AMBER||this.nB_==LightColour.AMBER)&&(this.sA_==LightColour.AMBER||this.sB_==LightColour.AMBER))
						{
							result =   new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
							return result;
						}
						else
						{
							result = new TestOracle(LightColour.AMBER,LightColour.AMBER,LightColour.AMBER,LightColour.AMBER,LightColour.RED,false);
							return result;
						}
					}
					else
					{
						result = new TestOracle(LightColour.AMBER,LightColour.AMBER,LightColour.AMBER,LightColour.AMBER,LightColour.RED,false);
						return result;
					}
				}
				
			}
			else if(tick>=3&&tick<7)
			{
				if(this.nA_==LightColour.RED&&this.nB_==LightColour.RED&&this.sA_==LightColour.RED&&this.sB_==LightColour.RED&&this.m_==LightColour.GREEN)
				{
					result = new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
					return result;
				}
				else
				{
					if(this.m_==LightColour.GREEN)
					{
						if((this.nA_==LightColour.RED||this.nB_==LightColour.RED)&&(this.sA_==LightColour.RED||this.sB_==LightColour.RED))
						{
							result =   new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
							return result;
						}
						else
						{
							result = new TestOracle(LightColour.RED,LightColour.RED,LightColour.RED,LightColour.RED,LightColour.GREEN,false);
							return result;
						}
					}
					else
					{
						result = new TestOracle(LightColour.RED,LightColour.RED,LightColour.RED,LightColour.RED,LightColour.GREEN,false);
						return result;
					}
					
				}
			}
			else if(tick>=7&&tick<9)
			{
				if(this.nA_==LightColour.RED&&this.nB_==LightColour.RED&&this.sA_==LightColour.RED&&this.sB_==LightColour.RED&&this.m_==LightColour.AMBER)
				{
					result = new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
					return result;
				}
				else
				{
					if(this.m_==LightColour.AMBER)
					{
						if((this.nA_==LightColour.RED||this.nB_==LightColour.RED)&&(this.sA_==LightColour.RED||this.sB_==LightColour.RED))
						{
							result =   new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
							return result;
						}
						else
						{
							result = new TestOracle(LightColour.RED,LightColour.RED,LightColour.RED,LightColour.RED,LightColour.AMBER,false);
							return result;
						}
					}
					else
					{
						result = new TestOracle(LightColour.RED,LightColour.RED,LightColour.RED,LightColour.RED,LightColour.AMBER,false);
						return result;
					}
					
					
				}
			}
			
		}
		else
		{
			if(tick>=0&&tick<=9)
			{
				if((this.nA_!=LightColour.NONE||this.nB_!=LightColour.NONE)&&(this.sA_!=LightColour.NONE||this.sB_!=LightColour.NONE)&&(this.m_!=LightColour.NONE))//greengreen green green red
				{
					
					result =   new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,true);
					return result;
				}
				else
				{
					result =   new TestOracle(this.nA_,this.nB_,this.sA_,this.sB_,this.m_,false);
					return result;
				}
			}
		}
		return result;
	
}
}
