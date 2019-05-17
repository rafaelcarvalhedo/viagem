package br.pucgoias.viagem.negocio;

import br.pucgoias.viagem.util.ViagemException;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable>  {

    /**
     * Inclui uma pessoa
     * @param pessoa
     * @return
     * @throws ViagemException
     */
    public T incluir(T obj) throws ViagemException;

    /**
     * Altera uma pessoa
     * @param pessoa
     * @return
     * @throws ViagemException
     */
    public T alterar(T obj) throws ViagemException;

    /**
     * Exclui uma pessoa
     * @param id
     * @throws ViagemException
     */
    public void excluir(ID id) throws ViagemException;

    /**
     * Consulta uma pessoa pelo identificador
     * @param id
     * @return
     * @throws ViagemException
     */
    public T consultar(ID id) throws ViagemException;

    /**
     * Lista todas as pessoas cadastradas
     * @return
     * @throws ViagemException
     */
    public List<T> listar() throws ViagemException;
}
