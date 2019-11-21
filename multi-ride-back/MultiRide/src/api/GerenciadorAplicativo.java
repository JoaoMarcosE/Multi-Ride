package api;

import interfaces.IApi;

public class GerenciadorAplicativo {
	
	void ChamarAplicativo(TipoAplicativo aplicativo, long destInicial, long destFinal) {
		
		IApi api = AppFactory.create(aplicativo);
		
		Destino destinoInicial = new Destino();
		destinoInicial.setDestino(destInicial);
		
		Destino destinoFinal = new Destino();
		destinoFinal.setDestino(destFinal);
		
		api.ChamarAplicativo(destinoInicial, destinoFinal);
		
		
	}

}
