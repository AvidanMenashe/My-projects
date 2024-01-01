package BarberShopComponents;


// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------
public class Service {
	private
		String _serviceName;
		int _servicePrice;
		int _exspenesFromService;
		
	
	//Constructors:
	public Service( String serviceName, int servicePrice, int exspenesFromService) {
		this._serviceName = serviceName;
		this._servicePrice = servicePrice;
		this._exspenesFromService = exspenesFromService;
	}
	
	public Service() {
		this._serviceName = " ";
		this._servicePrice = 0;
		this._exspenesFromService = 0;
	}
	
	//Copy Constructor:
	public Service( Service other) {
		this._serviceName = other.getServiceName();
		this._servicePrice = other.getServicePrice();
		this._exspenesFromService = other.getExspenesFromService();
	}
	
	
	
	//Setters and Getters:
	public String getServiceName () {return this._serviceName;}
	
	public int getServicePrice() {return this._servicePrice;}

	public int getExspenesFromService() {return this._exspenesFromService;}
	
	public void setName(String serviceName){ this._serviceName = serviceName;}
	
	public void setPrice(int servicePrice) {this._servicePrice = servicePrice;}

	public void setExspenesFromService(int exspenesFromService) {this._exspenesFromService = exspenesFromService;}
	
	public Service getService (){
		// we create a new object and return it to avoid returning a reference to the same object
		Service s = new Service();
		s.setName(this.getServiceName());
		s.setPrice(this.getServicePrice());
		s.setExspenesFromService(this.getExspenesFromService());
		
		return s;
	}
	
	//this function is used to check if the name of the customer contain only letters
	private boolean isValidServiceName(String serviceName)
	{
		int comperName=serviceName.compareTo(" ");
		boolean isSpace = false;
		if(comperName==0)
		{
			isSpace = true;
		}
		//return true only if the name contains only letters
		return serviceName.matches("[a-zA-Z\\s]+") && !isSpace;
	}
	
	public void setService (Service other) {
		this._serviceName= other.getServiceName();
		this._servicePrice= other.getServicePrice();
		this._exspenesFromService= other.getExspenesFromService();
	}
	
    @Override
	public String toString() {
		return "The Service is: " +
				this.getServiceName() +
				"\nThe Price of the service: " +
				this.getServicePrice() +
				"\nThe Expenses of the service: " +
				this.getExspenesFromService();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
