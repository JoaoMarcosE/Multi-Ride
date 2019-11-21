package api;

import interfaces.IApi;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AppFactory {
	
	private AppFactory() { }
	
	public static IApi create(TipoAplicativo tipo) {
		
		switch (tipo)
		{
			case UBER:
				return new ApiUber();
			case APP99:
				return new Api99();
			case CABIFY:
				return new ApiCabify();
		}

		throw new NotImplementedException();
	}

}
