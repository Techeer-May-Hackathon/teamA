package com.techeer.svproject.domain.address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import javax.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, UUID>{
    // 주소 추가


    // 주소 조회
    @Query(value="SELECT * FROM address s WHERE s.address_id = :address_id", nativeQuery = true)
    List <Address> searchAddress(@Param("address_id")int address_id);

    // 주소 수정
    @Transactional
    @Modifying
    @Query(value="UPDATE address s SET s.course_done =:course_done WHERE s.user_no = :user_no and s.course_id =:course_id",
            nativeQuery = true)
    void updateAddress(@Param("course_done")Boolean course_done,@Param("user_no")int user_no,@Param("course_id")int course_id);

    //주소 삭제
    @Query(value="DELETE FROM address s WHERE s.address_id = :address_id;", nativeQuery = true)
    void deleteAddress(@Param("address_id")int address_id);
}