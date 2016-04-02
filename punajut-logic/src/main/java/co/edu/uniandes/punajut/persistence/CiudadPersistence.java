/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.CiudadEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ja.poveda10
 */
@Stateless
public class CiudadPersistence {

    private static final Logger logger = Logger.getLogger(CiudadPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

    public CiudadEntity create(CiudadEntity entity) {
        logger.info("Creando una ciudad nueva");
        em.persist(entity);
        logger.info("Ciudad creada");
        return entity;
    }

    public CiudadEntity update(CiudadEntity entity) {
        logger.log(Level.INFO, "Actualizando ciudad con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando ciudad con id={0}", id);
        CiudadEntity entity = em.find(CiudadEntity.class, id);
        em.remove(entity);
    }

    public CiudadEntity find(Long id) {
        logger.log(Level.INFO, "Consultando ciudad con id={0}", id);
        return em.find(CiudadEntity.class, id);
    }

    public List<CiudadEntity> findAll() {
        logger.info("Consultando todas las ciudades");
        Query q = em.createQuery("select u from CiudadEntity u");
        return q.getResultList();
    }
}