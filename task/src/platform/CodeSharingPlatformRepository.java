package platform;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeSharingPlatformRepository extends CrudRepository<CodeSharingPlatformModel, Integer> {
    @Query(value = "Select c.code, c.date from code_sharing_platform_model c where c.id > :id " +
            "order by c.id DESC", nativeQuery = true)
    List<ICodeSharingPlatformProjection> findByIdGreaterThanByOrderByIdDesc(@Param("id") Integer id);

    @Query(value = "Select c.code, c.date from code_sharing_platform_model c order by c.id DESC", nativeQuery = true)
    List<ICodeSharingPlatformProjection> findAllByOrderByIdDesc();

    @Query(value = "Select c.code, c.date from code_sharing_platform_model c where c.id = :id", nativeQuery = true)
    ICodeSharingPlatformProjection findByIdProj(@Param("id") Integer id);
}
