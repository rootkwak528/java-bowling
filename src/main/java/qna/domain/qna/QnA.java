package qna.domain.qna;

import qna.domain.AbstractEntity;

import javax.persistence.Embeddable;

@Embeddable
public class QnA extends AbstractEntity {
    protected boolean deleted = false;

    protected QnA() {
    }

    QnA(boolean deleted) {
        this.deleted = deleted;
    }

    public QnA(Long id) {
        super(id);
    }

    public void setDelete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        return "QnA{" +
                "deleted=" + deleted +
                '}';
    }
}
