package mainClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GerenciaSons {

	private List<Som> listaDeSons;
	
	/**
	 * Construtor da classe.
	 */
	public GerenciaSons() {
		this.listaDeSons = new ArrayList<Som>();
	}


	public String postarSom(Usuario user, String link, String dataCriacao) {
		String id = "som" + (this.listaDeSons.size() + 1) + "ID";
		Som som = new Som(id, link, dataCriacao);
		this.listaDeSons.add(som);
		user.postarSom(id);
		return id;
	}

	public List<String> getPerfilMusical(Usuario user) {
		return user.getPerfilMusical();
	}

	public String getAtributoSom(String idSom, String atributo) {
		int sizeList = this.listaDeSons.size();
		for(int i=0;i<sizeList;i++){
			if(this.listaDeSons.get(i).getId().equals(idSom)){
				if(atributo.equals("dataCriacao")){
					return listaDeSons.get(i).getData();
				}
			}
		}
		return "";
	}
	
	public void seguirUsuario(Usuario userSeguidor, Usuario userSeguido) throws Exception{
		userSeguidor.addFontesDeSom(userSeguido.getId());
		userSeguido.addListaDeSeguidores(userSeguidor.getId());
		addVisaoDosSons(userSeguidor, userSeguido);
	}

	private void addVisaoDosSons(Usuario seguidor, Usuario seguido){
		List<String> perfilMusicalUserSeguido = getPerfilMusical(seguido);

		if(perfilMusicalUserSeguido != null){
			auxiliaAddSonsVisao(seguidor,perfilMusicalUserSeguido);
		}

	}
	
	public void auxiliaAddSonsVisao(Usuario user, List<String> perfilMusical){
		int sizeList = perfilMusical.size();
		for(int i=0; i<sizeList; i++){
			user.addEmVisaoDosSons(perfilMusical.get(i));
		}
	}
	
	/** Metodo que retorna a lista de visao de sons de um determinado Usuario.
	 * 
	 * @param user
	 * @return List<String>
	 */
	public Stack<String> getVisaoDosSons(Usuario user) {
		return user.getVisaoDosSons();
	}
}