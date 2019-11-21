package interfaces;

import api.Destino;

public interface IApi {
	
	IRetornoSolicitacao ChamarAplicativo(Destino destinoInicial, Destino destinoFinal);
}
