package com.infnet.web_rest_carlos_ferreira_tp3.services;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Produto;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> atualizar(Long id, Produto produto) {
        return produtoRepository.findById(id)
                .map(existingProduto -> {
                    existingProduto.setNome(produto.getNome());
                    existingProduto.setEmail(produto.getEmail());
                    existingProduto.setDescricao(produto.getDescricao());
                    existingProduto.setPreco(produto.getPreco());
                    return produtoRepository.save(existingProduto);
                });
    }

    public boolean deletar(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
