package comptes.mapper;



import org.apache.ibatis.annotations.*;
import jobplatform.model.Entreprise;

import java.util.List;

@Mapper
public interface EntrepriseMapper {

    // 🔹 Insertion d’une entreprise vide au moment de la création du compte
    @Insert("""
        INSERT INTO entreprise (username, email, phone, secteur, description, site, logo, status)
        VALUES (#{username}, #{email}, #{phone}, #{secteur}, #{description}, #{site}, #{logo}, #{status})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Entreprise entreprise);

    // 🔹 Récupération d’une entreprise par ID
    @Select("SELECT * FROM entreprise WHERE id = #{id}")
    Entreprise findById(Long id);

    // 🔹 Mise à jour
    @Update("""
        UPDATE entreprise
        SET username = #{username}, email = #{email}, phone = #{phone},
            secteur = #{secteur}, description = #{description},
            site = #{site}, logo = #{logo}, status = #{status}
        WHERE id = #{id}
    """)
    void update(Entreprise entreprise);

    // 🔹 Suppression
    @Delete("DELETE FROM entreprise WHERE id = #{id}")
    void delete(Long id);

    // 🔹 Liste complète
    @Select("SELECT * FROM entreprise")
    List<Entreprise> findAll();
}
