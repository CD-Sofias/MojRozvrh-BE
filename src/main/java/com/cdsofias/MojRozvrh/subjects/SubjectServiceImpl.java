package com.cdsofias.MojRozvrh.subjects;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectServiceImpl implements SubjectService{
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subjects createSubject(Subjects subject) {
        subject.setId(UUID.randomUUID());
        return subjectRepository.save(subject);
    }

    public List<Subjects> findAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subjects findSubjectById(UUID id) {
        Optional<Subjects> subject = subjectRepository.findById(id);
        return subject.orElse(null);
    }
    @Override
    public Subjects deleteSubjectById(UUID id) {
        subjectRepository.deleteById(id);
        return null;
    }

    public Subjects updateSubjectById(UUID id, Subjects newSubject) {
        Optional<Subjects> optionalSubject = subjectRepository.findById(id);
        if(optionalSubject.isPresent()){
            Subjects subject = optionalSubject.get();
            subject.setType(newSubject.getType());
            subject.setName(newSubject.getName());
            return subjectRepository.save(subject);
        }
        return null;
    }
}
