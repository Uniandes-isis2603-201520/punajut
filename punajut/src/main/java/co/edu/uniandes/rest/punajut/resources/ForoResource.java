/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;
import co.edu.uniandes.rest.punajut.dtos.CategoriaDTO;
import co.edu.uniandes.rest.punajut.dtos.DiscusionDTO;
import co.edu.uniandes.rest.punajut.dtos.ForoDTO;
import co.edu.uniandes.rest.punajut.dtos.RespuestaDTO;
import co.edu.uniandes.rest.punajut.exceptions.ForoLogicException;
import co.edu.uniandes.rest.punajut.mocks.ForoLogicMock;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("usuario/foro")
@Produces("application/json")
@RequestScoped
public class ForoResource {

	@Inject
	ForoLogicMock foroLogic;

	/**
	 * Obtiene el listado de personas.
	 * @return lista de ciudades
	 * @throws ForoLogicException excepción retornada por la lógica
	 */
    @GET
    public List<String> getCategorias() throws ForoLogicException {
        return foroLogic.getCategorias();
    }

    @GET
    @Path("{idCat: \\d+}")
    public CategoriaDTO getDiscusion(@PathParam("idCat") int idCat) throws ForoLogicException {
        return foroLogic.getCategoria(idCat);
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws ForoLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{idCat: \\d+}/{idDis: \\d+}")
    public DiscusionDTO getDiscusion(@PathParam("idCat") int idCat, @PathParam("idDis") int idDis) throws ForoLogicException {
        return foroLogic.getDiscusion(idCat, idDis);
    }

    @GET
    @Path("{idCat: \\d+}/{idDis: \\d+}/{idRes: \\d+}")
    public RespuestaDTO getDiscusion(@PathParam("idCat") int idCat,
            @PathParam("idDis") int idDis, @PathParam("idRes") int idRes) throws ForoLogicException {
        return foroLogic.getRespuesta(idCat, idDis, idRes);
    }

    /**
     * Agrega una ciudad
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws ForoLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public CityDTO createCity(CityDTO city) throws ForoLogicException {
        return cityLogic.createCity(city);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws ForoLogicException cuando no existe una ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public CityDTO updateCity(@PathParam("id") Long id, CityDTO city) throws ForoLogicException {
        return cityLogic.updateCity(id, city);
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws ForoLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id") Long id) throws ForoLogicException {
    	cityLogic.deleteCity(id);
    }

}


}