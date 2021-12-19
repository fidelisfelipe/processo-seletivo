package br.com.algoritimos.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.algoritimos.pojo.Pojo;
import br.com.algoritimos.repository.Repository;

/**
 * 
 * Anotações referentes aos verbos http
 * 
 * 
 * @DELETE = DELETE;
 * @GET = GET;
 * @HEAD = HEAD;
 * @OPTIONS = OPTIONS;
 * @POST = POST;
 * @PUT = PUT;
 * @PATCH = PATCH.
 *
 * 
 * 
 * @Path é utilizada para indicar um caminho. Ao defini-lo na classe, significa que este será o nosso endpoint
 * 
 * @author Fidelis.Guimaraes
 *
 */
@Path("/index")
public class IndexResource {
	private Repository _repositorio = new Repository();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pojo> get() {
        return _repositorio.GetAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pojo getById(@PathParam("id") int id) {
        return _repositorio.Get(id);
    }

    /**
     * Repare que os métodos também definem o tipo de conteúdo que irão produzir/retornar
     * E o tipo que irão consumir
     * @param Pojo
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Pojo Pojo)
    {
        try{
            _repositorio.Add(Pojo);
            return Response.status(Response.Status.CREATED).entity(Pojo).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id, Pojo Pojo)
    {
        Pojo p = _repositorio.Get(id);
        if(p == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        try{
            Pojo.setId(id);
            _repositorio.Edit(Pojo);
            return Response.status(Response.Status.OK).entity(Pojo).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }

    /**
     * O Jersey se encarregará de converter o objeto retornado para JSON e o JSON recebido para o tipo do objeto indicado.
     * Mas para que esta a conversão seja possível, é necessário definir a dependência abaixo (caso não esteja definida):
     * 
     * <dependency>
     * 		<groupId>org.glassfish.jersey.media</groupId>
     * 		<artifactId>jersey-media-json-binding</artifactId>
     * </dependency>
     * 
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id)
    {
        Pojo p = _repositorio.Get(id);
        if(p == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        try{
            _repositorio.Delete(id);
            return Response.status(Response.Status.OK).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }
}
