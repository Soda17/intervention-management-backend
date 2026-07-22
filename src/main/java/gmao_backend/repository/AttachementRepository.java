package gmao_backend.repository;


import gmao_backend.entity.Attachement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachementRepository extends JpaRepository<Attachement, Long> {
    List<Attachement> findByInterventionId(Long interventionId);
}
