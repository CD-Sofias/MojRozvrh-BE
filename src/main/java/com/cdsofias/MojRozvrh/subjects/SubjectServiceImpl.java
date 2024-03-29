package com.cdsofias.MojRozvrh.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{
    private final SubjectRepository subjectRepository;

    public Subject createSubject(CreateSubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.name());
        subject.setType(subjectDto.type());
        return subjectRepository.save(subject);
    }

    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject findSubjectById(UUID id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElse(null);
    }
    @Override
    public Subject deleteSubjectById(UUID id) {
        subjectRepository.deleteById(id);
        return null;
    }

    public Subject updateSubjectById(UUID id, Subject newSubject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if(optionalSubject.isPresent()){
            Subject subject = optionalSubject.get();
            subject.setType(newSubject.getType());
            subject.setName(newSubject.getName());
            return subjectRepository.save(subject);
        }
        return null;
    }
}
