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
    // 🔹 ✅ Filtrage dynamique
    @Select("""
        <script>
        SELECT * FROM entreprise
        WHERE 1=1
        <if test="secteur != null and secteur != ''">
            AND LOWER(secteur) LIKE LOWER(CONCAT('%', #{secteur}, '%'))
        </if>
        <if test="status != null and status != ''">
            AND LOWER(status) = LOWER(#{status})
        </if>
        <if test="email != null and email != ''">
            AND LOWER(email) LIKE LOWER(CONCAT('%', #{email}, '%'))
        </if>
        <if test="username != null and username != ''">
            AND LOWER(username) LIKE LOWER(CONCAT('%', #{username}, '%'))
        </if>
        ORDER BY id DESC
        </script>
    """)
    List<Entreprise> filterEntreprises(
            @Param("secteur") String secteur,
            @Param("status") String status,
            @Param("email") String email,
            @Param("username") String username
    );
}
