package com.cdsofias.MojRozvrh.classroom;

import com.cdsofias.MojRozvrh.address.Address;
import com.cdsofias.MojRozvrh.address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom getClassroom(UUID classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new IllegalStateException(
                        "Classroom with id " + classroomId + " does not exist"));
    }

    @Override
    public Classroom addNewClassroom(CreateClassroomDto classroomDto) {
        Optional<Classroom> classroomOptional = classroomRepository.findByType(classroomDto.type());
        if (classroomOptional.isPresent()) {
            throw new IllegalStateException("Classroom already exists");
        }

        Address address = addressRepository.findById(classroomDto.addressId())
                .orElseThrow(() -> new IllegalStateException(
                        "Address with id " + classroomDto.addressId() + " does not exist"));

        Classroom classroom = new Classroom();
        classroom.setType(classroomDto.type());
        classroom.setCapacity(classroomDto.capacity());
        classroom.setClassroomNumber(classroomDto.classroomNumber());
        classroom.setAddress(address);

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
