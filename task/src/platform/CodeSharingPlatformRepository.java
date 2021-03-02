package platform;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeSharingPlatformRepository extends CrudRepository<CodeSharingPlatformModel, String> {
    @Query(value = "Select c.code, c.date, c.time, c.views from code_sharing_platform_model c where c.time <= 0 and c.views <= 0" +
            "order by c.date DESC LIMIT 10", nativeQuery = true)
    List<ICodeSharingPlatformProjection> findByDateByOrderByDateDesc();

    /*@Query(value = "Select c.code, c.date, c.time, c.views from code_sharing_platform_model c order by c.date DESC", nativeQuery = true)
    List<ICodeSharingPlatformProjection> findAllByOrderByDateDesc();*/

    @Query(value = "Select c.code, c.date, c.time, c.views from code_sharing_platform_model c where c.id = :id", nativeQuery = true)
    ICodeSharingPlatformProjection findByIdProj(@Param("id") String id);
}
