package comptes.mapper;

import jobplatform.model.Offre;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OffreMapper {

    @Select("SELECT * FROM offre ORDER BY date_publication DESC")
    List<Offre> findAll();

    @Select("SELECT * FROM offre WHERE id = #{id}")
    Offre findById(@Param("id") Long id);

    @Insert("""
    	    INSERT INTO offre (poste, contrat, localisation, salaire, date_publication, entreprise_id)
    	    VALUES (#{poste}, #{contrat}, #{localisation}, #{salaire}, #{datePublication}, #{entrepriseId})
    	""")
    	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    	void insert(Offre offre);



    @Update("""
        UPDATE offre
        SET poste = #{poste},
            contrat = #{contrat},
            localisation = #{localisation},
            salaire = #{salaire},
            date_publication = #{datePublication},
            entreprise_id = #{entrepriseId},
            secteur = #{secteur},
            status = #{status}
        WHERE id = #{id}
    """)
    void update(Offre offre);

    @Delete("DELETE FROM offre WHERE id = #{id}")
    void delete(@Param("id") Long id);

    @Select("SELECT COUNT(*) FROM offre")
    int countAll();

    @Select("""
        SELECT * FROM offre
        ORDER BY date_publication DESC
        LIMIT #{limit} OFFSET #{offset}
    """)
    List<Offre> findAllPaged(@Param("limit") int limit, @Param("offset") int offset);

    @Select("""
        SELECT * FROM offre 
        WHERE (#{secteur} IS NULL OR LOWER(secteur) LIKE LOWER(CONCAT('%', #{secteur}, '%')))
          AND (#{contrat} IS NULL OR LOWER(contrat) LIKE LOWER(CONCAT('%', #{contrat}, '%')))
          AND (#{localisation} IS NULL OR LOWER(localisation) LIKE LOWER(CONCAT('%', #{localisation}, '%')))
          AND (#{salaireMin} IS NULL OR salaire >= #{salaireMin})
        ORDER BY date_publication DESC
        LIMIT #{limit} OFFSET #{offset}
    """)
    List<Offre> filterOffres(
        @Param("secteur") String secteur,
        @Param("contrat") String contrat,
        @Param("localisation") String localisation,
        @Param("salaireMin") Double salaireMin,
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    // 🆕 Ajout pour que la pagination filtrée fonctionne correctement :
    @Select("""
        SELECT COUNT(*) FROM offre 
        WHERE (#{secteur} IS NULL OR LOWER(secteur) LIKE LOWER(CONCAT('%', #{secteur}, '%')))
          AND (#{contrat} IS NULL OR LOWER(contrat) LIKE LOWER(CONCAT('%', #{contrat}, '%')))
          AND (#{localisation} IS NULL OR LOWER(localisation) LIKE LOWER(CONCAT('%', #{localisation}, '%')))
          AND (#{salaireMin} IS NULL OR salaire >= #{salaireMin})
    """)
    int countFiltered(
        @Param("secteur") String secteur,
        @Param("contrat") String contrat,
        @Param("localisation") String localisation,
        @Param("salaireMin") Double salaireMin
    );
}
