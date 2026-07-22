package gmao_backend.repository;

import gmao_backend.entity.Intervention;
import gmao_backend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByTechnicianId (Long technicianId);
    List<Intervention> findByStatus (Status status);
    List<Intervention> findAllByOrderByCreatedAtDesc();
}

