package br.pucgoias.viagem.negocio;

import br.pucgoias.viagem.util.ViagemException;

import java.io.Serializable;
import java.util.List;

/**
 * Define o escopo de operações base da interface de negocio
 * @param <T> Classe da Entidade
 * @param <ID> Classe do identificador da Entidade
 */
public interface BaseService<T, ID extends Serializable>  {

    /**
     * Inclui uma viagem
     * @param pessoa
     * @return
     * @throws ViagemException
     */
    public T incluir(T obj) throws ViagemException;

    /**
     * Altera uma viagem
     * @param pessoa
     * @return
     * @throws ViagemException
     */
    public T alterar(T obj) throws ViagemException;

    /**
     * Exclui uma viagem
     * @param id
     * @throws ViagemException
     */
    public void excluir(ID id) throws ViagemException;

    /**
     * Consulta uma viagem pelo identificador
     * @param id
     * @return
     * @throws ViagemException
     */
    public T consultar(ID id) throws ViagemException;

    /**
     * Lista todas as viagends cadastradas
     * @return
     * @throws ViagemException
     */
    public List<T> listar() throws ViagemException;
}
