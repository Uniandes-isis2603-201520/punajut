/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.rest.punajut.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import co.edu.uniandes.rest.punajut.converters.ItinerarioConverter;
import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;



import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author mi.arevalo10
 */
@Path("/viajero/{idViajero}/itinerarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ItinerarioResource {

    @Inject
    IItinerarioLogic itinerarioLogic;

    private static final Logger logger = Logger.getLogger(ItinerarioResource.class.getName());


    /**
     * Obtiene el listado de itinerarios.
     *
     * @param idViajero
     * @return lista de itinerarios
     * @throws ItinerarioLogicException excepción retornada por la lógica
     */
    @GET
    public List<ItinerarioDTO> getItinerarios(@PathParam("idViajero") Long idViajero) throws ItinerarioLogicException{
        logger.info("Se ejecuta método getItinerarios");
        List<ItinerarioEntity> itinerarios = itinerarioLogic.getItinerarios();
        return ItinerarioConverter.listEntity2DTO(itinerarios);
    }

    /**
     * Obtiene un  itinerario
     *
     * @param id identificador del itinerario
     * @return itinerario encontrada
     * @throws ItinerarioLogicException cuando el itinerario no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("id") Long id)
    {
        ItinerarioEntity itinerario;
        try{
         itinerario= itinerarioLogic.getItinerario(id);
        }
        catch(BusinessLogicException ex)
        {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }

        return ItinerarioConverter.fullEntity2DTO(itinerario);
    }

    /**
     * Agrega un itinerario
     *
     * @param itinerario itinerario a agregar
     * @return datos del itinerario a agregar
     * @throws ItinerarioLogicException cuando ya existe un itinerario con el id
     * suministrado
     */
    @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO itinerario) throws ItinerarioLogicException {
        logger.info("Se ejecuta método createItinerario");
        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerario);
        ItinerarioEntity newEntity = itinerarioLogic.createItinerario(entity);

        return ItinerarioConverter.fullEntity2DTO(newEntity);
    }

    /**
     * Actualiza los datos de un itinerario
     *
     * @param id identificador del itinerario a modificar
     * @return el itinerario modificado
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id
     * suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id,  ItinerarioDTO itinerario) throws ItinerarioLogicException {

        logger.log(Level.INFO, "Se ejecuta método updateItinerario con id={0}", id);
        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerario);
        entity.setId(id);
        ItinerarioEntity oldEntity;
        try{
        oldEntity= itinerarioLogic.getItinerario(id);
        }
        catch (BusinessLogicException ex){
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        entity.setFechaInicio(oldEntity.getFechaInicio());
        entity.setFechaFin(oldEntity.getFechaFin());

        ItinerarioEntity savedItinerario = itinerarioLogic.updateItinerario(entity);
        return ItinerarioConverter.fullEntity2DTO(savedItinerario);



    }

    /**
     * Elimina un itinerario
     *
     * @param id identificador de itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id
     * suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws ItinerarioLogicException
    {
        logger.log(Level.INFO, "Se ejecuta método deleteBook con id={0}", id);
        itinerarioLogic.deleteItinerario(id);

    }

}
