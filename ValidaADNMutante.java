package mx.com.xman.adn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/validaADN")
public class ValidaADNMutante {

	@GET
    @Path("{adn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String validaADNMutante(@PathParam("adn") String ADN) {
	
		String[] ADNArray = ADN.split(",");
		String respuesta = "";
		//String[] ADN2 = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		if(isMutant(ADNArray)){
			respuesta = "Es Mutante";
		}else{
			respuesta = "Es Humano";
		}
		
		return respuesta;
    }
	
	private boolean isMutant(String[] adn) {
		
		boolean resp = false;
		if(adn != null ){
			if(validateADNHorizontal(adn) | validateADNVertical(adn) | validateADNDiagonal(adn)){
				resp = true;
			}
		}
		
		return resp;
	}

	private static boolean validateADNHorizontal(String[] adn) {
		
		boolean resp = false;
		String valRes;
		char letra;
		for (int i = 0; i < adn.length; i++) {
			valRes = "";
			String dataHorizontal = adn[i];
			for (int j = 0; j < dataHorizontal.length(); j++) {
				if(j == 0){
					valRes += dataHorizontal.charAt(j);						
				}else{
					letra = dataHorizontal.charAt(j);
					if(letra == valRes.charAt(0)){
						valRes += "" + letra;
						if(valRes.length() == 4){
							System.out.println("Horizontal : " + valRes);
							return true;
						}
					}else{
						valRes = "" + letra;
					}
				}			
			}
		}
		return resp;
	}
	
	private static boolean validateADNVertical(String[] adn) {
		
		boolean resp = false;
		String valRes = "";
		char letra;
		int lengthY = adn.length;
		int lengthX = adn[0].length();
		
		try {
			for (int i = 0; i < lengthX; i++) {
				valRes = "";
				for (int j = 0; j < lengthY; j++) {
					if(j == 0){
						valRes += adn[j].charAt(i);						
					}else{
						letra = adn[j].charAt(i);
						if(letra == valRes.charAt(0)){
							valRes += "" + letra;
							if(valRes.length() == 4){
								System.out.println("Vertical : " + valRes);
								return true;
							}
						}else{
							valRes = "" + letra;
						}
					}
				}
				
			}
		} catch (Exception e) {
			System.out.println("La matriz no es simetrica");
			return false;
		}
		
		return resp;
	}
	
	private static boolean validateADNDiagonal(String[] adn) {
		
		boolean resp = false;
		String valRes = "";
		char letra;
		int lengthY = adn.length;
		int lengthX = adn[0].length();
		for (int i = 0; i < lengthX; i++) {
			valRes = "";
			for (int j = 0; j < lengthY; j++) {
					valRes = "";
					letra = adn[i].charAt(j);
					valRes += "" + letra;	
					if(i >= lengthX){
						continue;
					}
					if(j >= lengthY){
						continue;
					}
					int auxi = i + 1;
					int auxj = j + 1;
					if(auxi >= lengthX){
						continue;
					}
					if(auxj >= lengthY){
						continue;
					}

					try {
						while (adn[auxi].charAt(auxj) == valRes.charAt(0)) {						
							valRes += "" + adn[auxi].charAt(auxj);
							if(valRes.length() == 4){
								System.out.println("Diagonal : " + valRes);
								return true;
							}	
							auxi ++;
							auxj ++;
							if(auxi >= lengthX){
								break;
							}
							if(auxj >= lengthY){
								break;
							}					
						}		
					} catch (Exception e) {
						System.out.println("La matriz no es simetrica");
						return false;
					}
										
			}
			
		}
		return resp;
	}

}