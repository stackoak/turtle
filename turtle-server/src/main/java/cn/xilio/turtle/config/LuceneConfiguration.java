package cn.xilio.turtle.config;

import cn.xilio.turtle.core.lucene.LuceneTemplate;
import cn.xilio.turtle.core.lucene.LuceneConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Lucene 客户端配置
 */
@Configuration
public class LuceneConfiguration {
    @Bean
    public LuceneTemplate luceneClient(LuceneProperties properties) {
        String indexDir = properties.getIndexDir();
        LuceneConfig config = new LuceneConfig(indexDir, new IKAnalyzer());
        return new LuceneTemplate(config);
    }
}
