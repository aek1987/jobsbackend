package comptes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jobplatform.model.Candidat;
import jobplatform.model.Offre;

@Mapper
public interface CandidatMapper {

    // 🔹 Insertion d’un candidat vide au moment de la création du compte
    @Insert("""
        INSERT INTO candidat (username, email, fonction, phone, bio, cv, adresse, status, progression)
        VALUES (#{username}, #{email}, #{fonction}, #{phone}, #{bio}, #{cv}, #{adresse}, #{status}, #{progression})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "refId")
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
    

    // 🔹 Récupérer les favoris d’un candidat
    @Select("""
        SELECT o.* FROM offre o
        JOIN candidat_favoris cf ON o.id = cf.offre_id
        WHERE cf.candidat_id = #{candidatId}
    """)
    List<Offre> getFavoris(@Param("candidatId") Long candidatId);

    
    
    
    // 🔹 Ajouter une offre dans les favoris
    @Select("""
    	    SELECT COUNT(*) FROM candidat_favoris
    	    WHERE candidat_id = #{candidatId} AND offre_id = #{offreId}
    	""")
    	int existsFavori(@Param("candidatId") Long candidatId, @Param("offreId") Long offreId);

    @Insert("""
        INSERT INTO candidat_favoris (candidat_id, offre_id)
        VALUES (#{candidatId}, #{offreId})
    """)
    void addFavori(@Param("candidatId") Long candidatId, @Param("offreId") Long offreId);

    // 🔹 Supprimer une offre des favoris
    @Delete("""
        DELETE FROM candidat_favoris
        WHERE candidat_id = #{candidatId} AND offre_id = #{offreId}
    """)
    void removeFavori(@Param("candidatId") Long candidatId, @Param("offreId") Long offreId);
    
}
