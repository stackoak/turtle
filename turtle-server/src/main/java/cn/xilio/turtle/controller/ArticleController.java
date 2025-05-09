package cn.xilio.turtle.controller;

import cn.xilio.turtle.domain.Result;
import cn.xilio.turtle.domain.dto.CreateArticleDTO;
import cn.xilio.turtle.service.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Project Turtle
 * @Description 文章管理管理端接口
 * @Author xilio
 * @Website https://xilio.cn
 * @Copyright (c) 2025 xilio. All Rights Reserved.
 */
@Tag(name = "文章管理", description = "文章管理相关接口")
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "list", name = "获取文章列表")
    public Mono<Result> list(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size) {
        return articleService.queryAll(page, size).map(Result::success);
    }

    @PostMapping(value = "save", name = "保存文章")
    public Mono<Result> save(@RequestBody @Validated CreateArticleDTO dto) {
        return articleService.saveArticle(dto).map(Result::success);
    }

    @GetMapping(value = "get", name = "获取文章详情")
    public Mono<Result> get(@RequestParam("id") String id) {
        return articleService.get(id).map(Result::success);
    }

    @DeleteMapping(value = "delete", name = "删除文章")
    public Mono<Result> delete(@RequestParam("id") String id) {
        return articleService.deleteArticle(id)
                .then(Mono.just(Result.success()))
                .onErrorResume(ex -> Mono.just(Result.error("删除失败！")));
    }
}
