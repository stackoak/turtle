package cn.xilio.turtle.repository;

import cn.xilio.turtle.domain.entity.tag.Tag;
import cn.xilio.turtle.domain.dto.TagDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TagRepository extends ReactiveCrudRepository<Tag, String> {
    @Query("""
        SELECT t.id, t.name, COUNT(at.article_id) AS article_count
        FROM tag t
        LEFT JOIN article_tag at ON t.id = at.tag_id
        GROUP BY t.id
        HAVING COUNT(at.article_id) > 0
        ORDER BY article_count DESC
        LIMIT :limit OFFSET :offset
        """)
    Flux<TagDTO> findWithArticleCountPaginated(int limit, int offset);

    @Query("SELECT COUNT(1) FROM tag")
    Mono<Integer> countTags();
}
