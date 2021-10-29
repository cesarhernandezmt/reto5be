/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo3.retos.service;


import co.usa.ciclo3.retos.model.Reservation;
import co.usa.ciclo3.retos.model.report.ClientCount;
import co.usa.ciclo3.retos.model.report.ReservationStatus;
import co.usa.ciclo3.retos.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    
    public Optional<Reservation> getReservation(int idReservation) {
        return reservationRepository.getReservation(idReservation);
    }
    
    public Reservation save(Reservation reservation) {
        if(reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        }
        else {
            Optional<Reservation> reservationOptional = 
            reservationRepository.getReservation(reservation.getIdReservation());
            if(reservationOptional.isEmpty()) {
                return reservationRepository.save(reservation);
            }
            else {
                return reservation;
            }
        }
    }    
    
    public Reservation update(Reservation reservation) {
        if(reservation.getIdReservation() != null) {
            Optional<Reservation> reservationOptional = 
            reservationRepository.getReservation(reservation.getIdReservation());
            if(!reservationOptional.isEmpty()) {
                if(reservation.getStartDate() != null) {
                    reservationOptional.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate() != null) {
                    reservationOptional.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus() != null) {
                    reservationOptional.get().setStatus(reservation.getStatus());
                }
                if(reservation.getDoctor() != null) {
                    reservationOptional.get().setDoctor(reservation.getDoctor());
                }
                if(reservation.getClient() != null) {
                    reservationOptional.get().setClient(reservation.getClient());
                }
                if(reservation.getScore() != null) {
                    reservationOptional.get().setScore(reservation.getScore());
                }
                reservationRepository.save(reservationOptional.get());
                return reservationOptional.get();
            }
            else {
                return reservation;
            }
        }
        else {
            return reservation;
        }
    }
    
    public boolean deleteReservation(int idReservation) {
        Optional<Reservation> reservationOptional = getReservation(idReservation);
        if(!reservationOptional.isEmpty()) {
            reservationRepository.delete(reservationOptional.get());
            return true;
        }
        return false;
    }
    
    public ReservationStatus getAllStatus() {
        List<Reservation> completed = reservationRepository.getAllStatus("completed");
        List<Reservation> cancelled = reservationRepository.getAllStatus("cancelled");
        return new ReservationStatus(completed.size(), cancelled.size());
    }
    
    public List<Reservation> getAllTimeInterval(String dateOne, String dateTwo) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateA = new Date();
        Date dateB = new Date();
        
        try{
            dateA = parser.parse(dateOne);
            dateB = parser.parse(dateTwo);
        }
        catch(ParseException event) {
            event.printStackTrace();
        }
        if(dateA.before(dateB)) {
            return reservationRepository.getAllTimeInterval(dateA, dateB);
        }
        else {
            return new ArrayList<>();
        }
    }
   
    public List<ClientCount> getTopClients() {
        return reservationRepository.getTopClients();
    }
    
}
