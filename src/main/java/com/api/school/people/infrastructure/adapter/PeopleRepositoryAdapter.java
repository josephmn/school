package com.api.school.people.infrastructure.adapter;

import org.springframework.stereotype.Service;
import com.api.school.exception.types.AlreadyExistsException;
import com.api.school.exception.types.NotFoundException;
import com.api.school.people.domain.ports.PeoplesRepositoryPort;
import com.api.school.people.infrastructure.repository.PeopleRepositoryReactive;
import com.api.school.util.PeopleMapper;
import com.api.school.util.UtilFunctions;
import com.openapi.generate.model.RequestPeopleDto;
import com.openapi.generate.model.ResponseDTO;
import com.openapi.generate.model.ResponsePeopleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * PeopleRepositoryAdapter.
 *
 * @author Joseph Magallanes
 * @since 2024-08-26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PeopleRepositoryAdapter implements PeoplesRepositoryPort {

    private final PeopleRepositoryReactive peopleRepositoryReactive;
    private final PeopleMapper peopleMapper;

    @Override
    public Flux<ResponsePeopleDto> getAllPeoples() {
        log.info("Start execute method getAllPeoples");
        return peopleRepositoryReactive.findAll()
            .map(peopleMapper::peopleToResponse)
            .doOnTerminate(() -> log.info("Finished execute method getAllPeoples"));
    }

    @Override
    public Mono<ResponsePeopleDto> getPeopleById(Integer id) {
        log.info("Start execute method getPeopleById");
        return peopleRepositoryReactive.findById(id)
            .map(peopleMapper::peopleToResponse)
            .switchIfEmpty(Mono.error(new AlreadyExistsException(
                "People not found with id: %s", id)))
            .doOnTerminate(() -> log.info("Finished execute method getPeopleById"));
    }

    @Override
    public Mono<ResponsePeopleDto> createPeople(RequestPeopleDto requestDto) {
        log.info("Start execute method createPeople");
        if (requestDto.getDateRegister() == null || requestDto.getDateRegister().isBlank()) {
            final String createDate = UtilFunctions.getCurrentDate();
            requestDto.setDateRegister(createDate);
        }
        final String documentNumber = requestDto.getNumberDocument();
        return peopleRepositoryReactive.findByDocumentNumber(documentNumber)
            .flatMap(existingCustomer -> Mono.error(new AlreadyExistsException(
                "People exists with document number: %s", documentNumber)))
            .switchIfEmpty(Mono.defer(() -> {
                log.info("People before create: {}", requestDto);
                return peopleRepositoryReactive.save(peopleMapper.requestToPeople(requestDto))
                    .map(peopleMapper::peopleToResponse)
                    .doOnNext(customerAfter -> log.info(
                        "People after create: {}", customerAfter));
            }))
            .cast(ResponsePeopleDto.class)
            .doOnTerminate(() -> log.info("Finished execute method createPeople"));
    }

    @Override
    public Mono<ResponsePeopleDto> updatePeopleById(Integer id, RequestPeopleDto requestDto) {
        log.info("Start execute method updatePeopleById");
        return peopleRepositoryReactive.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException(
                "People not found with id: %s", id)))
            .flatMap(existingPeople -> {
                if (!existingPeople.getDocumentNumber().equals(requestDto.getNumberDocument())) {
                    return Mono.error(new NotFoundException(
                        "People with document %s, doesn't correspond the object",
                        requestDto.getNumberDocument()));
                }
                else {
                    final var updatedPeople = peopleMapper.requestToPeople(requestDto);
                    updatedPeople.setId(id);
                    return peopleRepositoryReactive.save(updatedPeople)
                        .map(peopleMapper::peopleToResponse);
                }
            })
            .doOnTerminate(() -> log.info("Finished execute method updatePeopleById"));
    }

    @Override
    public Mono<ResponseDTO> deletePeopleById(Integer id) {
        log.info("Start execute method deletePeopleById");
        return peopleRepositoryReactive.existsById(id)
            .flatMap(exists -> {
                if (exists) {
                    return peopleRepositoryReactive.deleteById(id)
                        .then(Mono.just(new ResponseDTO()
                            .code("200")
                            .message("People deleted successfully"))
                        );
                }
                else {
                    return Mono.error(new NotFoundException(
                        "People not found with id: %s", id));
                }
            })
            .doOnTerminate(() -> log.info("Finished execute method deletePeopleById"));
    }

    @Override
    public Flux<ResponsePeopleDto> getListPeopleByName(String name) {
        log.info("Start execute method getListPeopleByName");
        return peopleRepositoryReactive.findByFirstNameContainingIgnoreCase(name)
            .map(peopleMapper::peopleToResponse)
            .doOnTerminate(() -> log.info("Finished execute method getListPeopleByName"));
    }

    @Override
    public Flux<ResponsePeopleDto> getListPeopleByLastName(String lastName) {
        log.info("Start execute method getListPeopleByLastName");
        return peopleRepositoryReactive.findByLastNameContainingIgnoreCase(lastName)
            .map(peopleMapper::peopleToResponse)
            .doOnTerminate(() -> log.info("Finished execute method getListPeopleByLastName"));
    }

    @Override
    public Mono<ResponsePeopleDto> updatePeopleByDocument(String document, RequestPeopleDto requestDto) {
        log.info("Start execute method updatePeopleByDocument");
        return peopleRepositoryReactive.findByDocumentNumber(document)
            .switchIfEmpty(Mono.error(new NotFoundException(
                "People not found with document: %s", document)))
            .flatMap(existingPeople -> {
                if (!existingPeople.getDocumentNumber().equals(requestDto.getNumberDocument())) {
                    return Mono.error(new NotFoundException(
                        "People with document %s, doesn't correspond the object",
                        requestDto.getNumberDocument()));
                }
                else {
                    final var updatedPeople = peopleMapper.requestToPeople(requestDto);
                    updatedPeople.setId(existingPeople.getId());
                    return peopleRepositoryReactive.save(updatedPeople)
                        .map(peopleMapper::peopleToResponse);
                }
            })
            .doOnTerminate(() -> log.info("Finished execute method updatePeopleByDocument"));
    }
}
