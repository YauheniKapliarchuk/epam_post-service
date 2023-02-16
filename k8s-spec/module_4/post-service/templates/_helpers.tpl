{{- define "post-service.labels"}}
    labels:
        data: {{ now | htmlDate }}
        version: {{ .Chart.version }}
{{- end }}
