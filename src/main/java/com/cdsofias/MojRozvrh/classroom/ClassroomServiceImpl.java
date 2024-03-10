package com.cdsofias.MojRozvrh.classroom;

import com.cdsofias.MojRozvrh.address.Address;
import com.cdsofias.MojRozvrh.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository, AddressRepository addressRepository) {
        this.classroomRepository = classroomRepository;
        this.addressRepository = addressRepository;
    }



    @Override
    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom getClassroom(UUID classroomId) { // новый метод
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new IllegalStateException(
                        "Classroom with id " + classroomId + " does not exist"));
    }

    @Override
    public Classroom addNewClassroom(Classroom classroom) {
        Optional<Classroom> classroomOptional = classroomRepository
                .findByType(classroom.getType());
        if (classroomOptional.isPresent()) {
            throw new IllegalStateException("Classroom already exists");
        }

        if (classroom.getAddress() != null) {
            Address address = addressRepository.findById(classroom.getAddress().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Address with id " + classroom.getAddress().getId() + " does not exist"));
            classroom.setAddress(address);
        }

        return classroomRepository.saveAndFlush(classroom);
    }

    @Override
    public void deleteClassroom(UUID classroomId) {
        boolean exists = classroomRepository.existsById(classroomId);
        if (!exists) {
            throw new IllegalStateException(
                    "Classroom with id " + classroomId + " does not exist");
        }
        classroomRepository.deleteById(classroomId);
    }


    public Classroom updateClassroom(UUID classroomId, Classroom classroom) {
        Classroom classroomToUpdate = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new IllegalStateException(
                        "Classroom with id " + classroomId + " does not exist"));

        if (classroom.getType() != null && !classroom.getType().isEmpty() && !Objects.equals(classroomToUpdate.getType(), classroom.getType())) {
            classroomToUpdate.setType(classroom.getType());
        }

        if (classroom.getAddress() != null) {
            Address address = addressRepository.findById(classroom.getAddress().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Address with id " + classroom.getAddress().getId() + " does not exist"));
            classroomToUpdate.setAddress(address);
        }

        return classroomRepository.save(classroomToUpdate);
    }

}
