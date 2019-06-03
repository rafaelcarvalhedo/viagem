package br.pucgoias.viagem.persistencia;

import br.pucgoias.viagem.entidade.Viagem;
import org.springframework.stereotype.Repository;

/**
 * Implementa as operações de persistencia da entidade de  Viagem
 */
@Repository
public class ViagemDAOImpl extends GenericoDAOImpl<Viagem, Integer> implements
        ViagemDAO {

}
