package com.api.school.people.infrastructure.web;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import com.api.school.people.domain.ports.PeoplesRepositoryPort;
import com.openapi.generate.api.PeopleApi;
import com.openapi.generate.model.RequestPeopleDto;
import com.openapi.generate.model.ResponseDTO;
import com.openapi.generate.model.ResponsePeopleDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PeopleController.
 *
 * @author Joseph Magallanes
 * @since 2024-08-26
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PeopleController implements PeopleApi {

    private final PeoplesRepositoryPort peoplesRepositoryPort;

    @Override
    public Mono<ResponseEntity<Flux<ResponsePeopleDto>>> getAllPeople(
        ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(this.peoplesRepositoryPort.getAllPeoples()))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<ResponsePeopleDto>> getPeopleById(
        Integer id,
        ServerWebExchange exchange) {
        return this.peoplesRepositoryPort.getPeopleById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<ResponsePeopleDto>> createPeople(
        Mono<RequestPeopleDto> requestPeopleDto,
        ServerWebExchange exchange) {
        return requestPeopleDto.flatMap(dto ->
            this.peoplesRepositoryPort.createPeople(dto)
                .map(responseDto -> ResponseEntity
                    .created(URI.create("/api/v1/people/"))
                    .body(responseDto))
                .defaultIfEmpty(ResponseEntity.notFound().build())
        );
    }

    @Override
    public Mono<ResponseEntity<ResponsePeopleDto>> updatePeopleById(
        Integer id,
        Mono<RequestPeopleDto> requestPeopleDto,
        ServerWebExchange exchange) {
        return requestPeopleDto.flatMap(dto ->
            this.peoplesRepositoryPort.updatePeopleById(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
        );
    }

    @Override
    public Mono<ResponseEntity<ResponseDTO>> deletePeopleById(
        Integer id,
        ServerWebExchange exchange) {
        return this.peoplesRepositoryPort.deletePeopleById(id)
            .map(response -> ResponseEntity.ok().body(response))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<ResponsePeopleDto>>> getListPeopleByName(
        String name,
        ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(this.peoplesRepositoryPort.getListPeopleByName(name)))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<ResponsePeopleDto>>> getListPeopleByLastName(
        String lastName,
        ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(this.peoplesRepositoryPort.getListPeopleByLastName(lastName)))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<ResponsePeopleDto>> updatePeopleByDocument(
        String document,
        Mono<RequestPeopleDto> requestPeopleDto,
        ServerWebExchange exchange) {
        return requestPeopleDto.flatMap(dto ->
            this.peoplesRepositoryPort.updatePeopleByDocument(document, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
        );
    }
}
