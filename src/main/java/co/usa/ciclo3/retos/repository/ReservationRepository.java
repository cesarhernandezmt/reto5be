/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo3.retos.repository;

import co.usa.ciclo3.retos.model.Client;
import co.usa.ciclo3.retos.model.Reservation;
import co.usa.ciclo3.retos.model.report.ClientCount;
import co.usa.ciclo3.retos.repository.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    
    public Optional<Reservation> getReservation(int idReservation){
        return reservationCrudRepository.findById(idReservation);
    }
    
    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }    

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getAllStatus (String status) {
        return reservationCrudRepository.findAllByStatus(status);
    }
 
    public List<Reservation> getAllTimeInterval (Date dateOne, Date dateTwo) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
    
    public List<ClientCount> getTopClients() {
        List<ClientCount> result = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationsByClient();
        for(int i = 0; i < report.size(); i++) {
            result.add(new ClientCount((Long) report.get(i)[1], (Client) report.get(i)[0]));  
        }
        return result;
    }

}
