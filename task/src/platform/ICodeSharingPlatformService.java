package platform;

import java.util.List;

public interface ICodeSharingPlatformService {
    List<ICodeSharingPlatformProjection> getLatestCodeSnippets();
    ICodeSharingPlatformProjection getNthCodeSnippet(Integer N);
    String createNewCodeSnippet(String newCode);
}
