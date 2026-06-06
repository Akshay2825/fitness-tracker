package com.fitness.activityservice.serviceimpl;

import com.fitness.activityservice.Repository.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    public final ActivityRepository activityRepository;
    @Override
    public ActivityResponse trackActivity(ActivityRequest request) {
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .caloriesBurned(request.getCaloriesBurned())
                .duration(request.getDuration())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);

        return mapToReponse(savedActivity);
    }

    private ActivityResponse mapToReponse(Activity activity) {

        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setDuration(activity.getDuration());
        response.setType(activity.getType());
        response.setCreatedAt(activity.getCreatedAt());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setUpdatedAt(activity.getUpdatedAt());

        return response;
    }
}
