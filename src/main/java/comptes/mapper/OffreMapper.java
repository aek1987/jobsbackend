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

    // Filtrage avec expérience et salaire min/max
    @Select("""
    <script>
    SELECT * FROM offre
    <where>
        <if test="secteur != null and secteur != ''">
            AND LOWER(secteur) LIKE LOWER(CONCAT('%', #{secteur}, '%'))
        </if>
        <if test="contrat != null and contrat != ''">
            AND LOWER(contrat) LIKE LOWER(CONCAT('%', #{contrat}, '%'))
        </if>
        <if test="localisation != null and localisation != ''">
            AND LOWER(localisation) LIKE LOWER(CONCAT('%', #{localisation}, '%'))
        </if>
        <if test="teletravail != null and teletravail != ''">
            AND LOWER(teletravail) LIKE LOWER(CONCAT('%', #{teletravail}, '%'))
        </if>
        <if test="experience != null and experience != ''">
            AND LOWER(experience) LIKE LOWER(CONCAT('%', #{experience}, '%'))
        </if>
        <if test="salaireMin != null">
            AND salaire &gt;= #{salaireMin}
        </if>
        <if test="salaireMax != null">
            AND salaire &lt;= #{salaireMax}
        </if>
    </where>
    ORDER BY date_publication DESC
    LIMIT #{limit} OFFSET #{offset}
    </script>
    """)
    List<Offre> filterOffres(
        @Param("secteur") String secteur,
        @Param("contrat") String contrat,
        @Param("localisation") String localisation,
        @Param("teletravail") String teletravail,
        @Param("experience") String experience,
        @Param("salaireMin") Double salaireMin,
        @Param("salaireMax") Double salaireMax,
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    @Select("""
    <script>
    SELECT COUNT(*) FROM offre
    <where>
        <if test="secteur != null and secteur != ''">
            AND LOWER(secteur) LIKE LOWER(CONCAT('%', #{secteur}, '%'))
        </if>
        <if test="contrat != null and contrat != ''">
            AND LOWER(contrat) LIKE LOWER(CONCAT('%', #{contrat}, '%'))
        </if>
        <if test="localisation != null and localisation != ''">
            AND LOWER(localisation) LIKE LOWER(CONCAT('%', #{localisation}, '%'))
        </if>
        <if test="experience != null and experience != ''">
            AND LOWER(experience) LIKE LOWER(CONCAT('%', #{experience}, '%'))
        </if>
        <if test="salaireMin != null">
            AND salaire &gt;= #{salaireMin}
        </if>
        <if test="salaireMax != null">
            AND salaire &lt;= #{salaireMax}
        </if>
    </where>
    </script>
    """)
    int countFiltered(
        @Param("secteur") String secteur,
        @Param("contrat") String contrat,
        @Param("localisation") String localisation,
        @Param("experience") String experience,
        @Param("salaireMin") Double salaireMin,
        @Param("salaireMax") Double salaireMax
    );

}
