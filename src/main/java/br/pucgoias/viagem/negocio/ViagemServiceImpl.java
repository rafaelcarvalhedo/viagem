package br.pucgoias.viagem.negocio;

import br.pucgoias.viagem.entidade.Viagem;
import br.pucgoias.viagem.persistencia.ViagemDAO;
import br.pucgoias.viagem.util.ViagemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ViagemServiceImpl implements ViagemService {

    @Autowired
    private ViagemDAO viagemDao;

    @Override
    public Viagem incluir(Viagem obj) throws ViagemException {
        return viagemDao.incluir(obj);
    }

    @Override
    public Viagem alterar(Viagem obj) throws ViagemException {
        return viagemDao.alterar(obj);
    }

    @Override
    public void excluir(Integer integer) throws ViagemException {
        viagemDao.excluir(integer);
    }

    @Override
    public Viagem consultar(Integer integer) throws ViagemException {
        return  viagemDao.consultar(integer);
    }

    @Override
    public List<Viagem> listar() throws ViagemException {
        return viagemDao.listar();
    }
}
