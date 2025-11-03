package comptes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jobplatform.model.Candidat;

@Mapper
public interface CandidatMapper {

    // 🔹 Insertion d’un candidat vide au moment de la création du compte
    @Insert("""
        INSERT INTO candidat (username, email, fonction, phone, bio, cv, adresse, status, progression)
        VALUES (#{username}, #{email}, #{fonction}, #{phone}, #{bio}, #{cv}, #{adresse}, #{status}, #{progression})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Candidat candidat);

    // 🔹 Récupération d’un candidat par ID
    @Select("SELECT * FROM candidat WHERE id = #{id}")
    Candidat findById(Long id);

    // 🔹 Mise à jour des informations principales
    @Update("""
        UPDATE candidat
        SET username = #{username}, email = #{email}, fonction = #{fonction},
            phone = #{phone}, bio = #{bio}, cv = #{cv}, adresse = #{adresse},
            status = #{status}, progression = #{progression}
        WHERE id = #{id}
    """)
    void update(Candidat candidat);

    // 🔹 Suppression d’un candidat
    @Delete("DELETE FROM candidat WHERE id = #{id}")
    void delete(Long id);

    // 🔹 Liste complète (utile pour l’admin)
    @Select("SELECT * FROM candidat")
    List<Candidat> findAll();
}
