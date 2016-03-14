/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;
import co.edu.uniandes.rest.punajut.dtos.EventoUsuarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import co.edu.uniandes.rest.punajut.mocks.EventoUsuarioLogicMock;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


/**
 *
 * @author ls.hernandez10
 */
@Path("eventoVisual")
public class eventoUsuarioResource {
    	@Inject
	EventoUsuarioLogicMock eventoUsuarioLogic;

	/**
	 * Obtiene el listado de los eventos a los que asistirá el usuario en una ciudad.
	 * @return lista de eventos
	 * @throws ItinerarioLogicException excepción retornada por la lógica
	 */
    @GET
    public List<EventoUsuarioDTO> getEventoUsuario() throws ItinerarioLogicException {
    //    return eventoUsuarioLogic.getCities();
    return null;
    }

    /**
     * Obtiene uno de los eventos de los que asistirá el viajero
     * @param id identificador de la ciudad
     * @return evento encontrada
     * @throws ItinerarioLogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}")
    public EventoUsuarioDTO getCity(@PathParam("id") Long id) throws ItinerarioLogicException {
    //    return eventoUsuarioLogic.getCity(id);
    return null;
    }

    /**
     * Registra un evento al que asistirá el viajero
     * @param evento evento a agregar
     * @return datos del evento a agregar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public EventoUsuarioDTO registrarEventoUsuario(EventoUsuarioDTO evento) throws ItinerarioLogicException {
    //    return eventoUsuarioLogic.createCity(evento);
    return null;
    }

    /**
     * Modifica un evento de la lista del usuario.
     * @param id identificador del evento a modificar
     * @param evento evento a modificar
     * @return datos del evento modificado
     * @throws ItinerarioLogicException cuando no existe un evento con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public EventoUsuarioDTO modificarEventoUsuario(@PathParam("id") Long id, EventoUsuarioDTO evento) throws ItinerarioLogicException {
    //    return eventoUsuarioLogic.updateCity(id, evento);
    return null;
    }

    /**
     * Elimina los datos de un evento de la lista del usuario.
     * @param id identificador del evento a eliminar
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void borrarEventoUsuario(@PathParam("id") Long id) throws ItinerarioLogicException {
    //	eventoUsuarioLogic.deleteCity(id);
    }

}
