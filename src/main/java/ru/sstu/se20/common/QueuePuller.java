package ru.sstu.se20.common;

import ru.sstu.se20.DAL.DAO.AbstractHibernateDAO;
import ru.sstu.se20.DAL.DAO.ResultImagesDAO;
import ru.sstu.se20.DAL.Entities.ResultImagesEntity;
import ru.sstu.se20.DAL.Queue;
import ru.sstu.se20.DTO.JobDecoderDTO;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class QueuePuller {
    public static void scheduleFixedDelayTask() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            Queue queue = new Queue();
            while (queue.lenResult() > 0) {
                String result = queue.popResult();
                JobDecoderDTO jobDecoderDTO = JobEncoderDecoder.decode(result);
                int jobId = jobDecoderDTO.getJobId();
                AbstractHibernateDAO dao = new ResultImagesDAO();
                ResultImagesEntity entity = (ResultImagesEntity) dao.getById(jobId);
                entity.setResultImage(jobDecoderDTO.getBytesResultImage());
                dao.update(entity);
            }
        };

        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 5, 30, TimeUnit.SECONDS);
    }
}
