/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.usa.ciclo3.retos.vcontroller;


import co.usa.ciclo3.retos.model.Reservation;
import co.usa.ciclo3.retos.model.report.ClientCount;
import co.usa.ciclo3.retos.model.report.ReservationStatus;
import co.usa.ciclo3.retos.service.ReservationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, 
             RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return reservationService.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int idReservation) {
        return reservationService.getReservation(idReservation);
    } 
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }    
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int idReservation) {
        return reservationService.deleteReservation(idReservation);
    }
    
    @GetMapping("/report-status")
    public ReservationStatus getReservationsStatus() {
        return reservationService.getAllStatus();
    }
    
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsInterval(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) {
        return reservationService.getAllTimeInterval(dateOne, dateTwo);
    }
    
    @GetMapping("/report-clients")   
    public List<ClientCount> getTopClients() {
        return reservationService.getTopClients();
    }
    
}
