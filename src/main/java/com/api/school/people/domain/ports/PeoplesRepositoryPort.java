package com.api.school.people.domain.ports;

import com.openapi.generate.model.RequestPeopleDto;
import com.openapi.generate.model.ResponseDTO;
import com.openapi.generate.model.ResponsePeopleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PeoplesRepositoryPort.
 *
 * @author Joseph Magallanes
 * @since 2025-08-26
 */
public interface PeoplesRepositoryPort {
    Flux<ResponsePeopleDto> getAllPeoples();
    Mono<ResponsePeopleDto> createPeople(RequestPeopleDto requestDto);
    Mono<ResponsePeopleDto> getPeopleById(Integer id);
    Mono<ResponsePeopleDto> updatePeopleById(Integer id, RequestPeopleDto requestDto);
    Mono<ResponseDTO> deletePeopleById(Integer id);
    Flux<ResponsePeopleDto> getListPeopleByName(String name);
    Flux<ResponsePeopleDto> getListPeopleByLastName(String lastName);
    Mono<ResponsePeopleDto> updatePeopleByDocument(String document, RequestPeopleDto requestDto);
}
