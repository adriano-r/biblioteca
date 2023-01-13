package br.com.gerenciador.biblioteca.service;

import br.com.gerenciador.biblioteca.model.UsersModel;
import br.com.gerenciador.biblioteca.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email){
        if (login == null || password == null) {
            return null;
        } else {
            if (usersRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Login duplicado!");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String login, String password){
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    public List<UsersModel> listUsers(){
        return usersRepository.findAll();
    }

    public Optional<UsersModel> getUser(Integer id){
        return usersRepository.findById(id);
    }

    public UsersModel updateUser(Integer id, UsersModel model) {
        Optional<UsersModel> updatedUser = usersRepository.findById(id);
        UsersModel usersModel = updatedUser.get();
        usersModel.setLogin(model.getLogin());
        usersModel.setEmail(model.getEmail());
        usersModel.setPassword(model.getPassword());
        return usersRepository.save(usersModel);
    }

    public void deleteUser(Integer id){
        usersRepository.deleteById(id);
    }
}
